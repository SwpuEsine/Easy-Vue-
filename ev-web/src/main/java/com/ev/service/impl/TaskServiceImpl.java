package com.ev.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.ev.entity.QuartzJob;
import com.ev.scheduler.JobParameter;
import com.ev.service.QuartzJobService;
import com.ev.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.support.TaskUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author
 * @create 2019-03-11 下午3:20
 **/
@Service
@Slf4j
public class TaskServiceImpl implements TaskService{
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private QuartzJobService quartzJobService;
    @Override
    public QuartzJob getJob(String jobName, String jobGroup) throws SchedulerException {
        QuartzJob job = null;
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if (null != trigger) {
            job = createJob(jobName, jobGroup, scheduler, trigger);
        }
        return job;
    }


    private QuartzJob createJob(String jobName, String jobGroup, Scheduler scheduler, Trigger trigger)
            throws SchedulerException {
        QuartzJob job;
        job = new QuartzJob();
        job.setJobName(jobName);
        job.setJobGroup(jobGroup);
        job.setDescription("触发器:" + trigger.getKey());
        job.setNextTime(trigger.getNextFireTime());
        job.setPreviousTime(trigger.getPreviousFireTime());

        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
        job.setJobStatus(triggerState.name());
        if(trigger instanceof CronTrigger) {
            CronTrigger cronTrigger = (CronTrigger)trigger;
            String cronExpression = cronTrigger.getCronExpression();
            job.setCronExpression(cronExpression);
        }
        return job;
    }


    /** * 获取所有任务 * @return * @throws SchedulerException */
    public List<QuartzJob> getAllJobs() throws SchedulerException{
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        List<QuartzJob> jobList = new ArrayList<QuartzJob>();
        List<? extends Trigger> triggers;
        QuartzJob job;
        for (JobKey jobKey : jobKeys) {
            triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                job = createJob(jobKey.getName(), jobKey.getGroup(), scheduler, trigger);
                jobList.add(job);
            }
        }

        return jobList;
    }

    /** * 所有正在运行的job * * @return * @throws SchedulerException */
    public List<QuartzJob> getRunningJob() throws SchedulerException {
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
        List<QuartzJob> jobList = new ArrayList<QuartzJob>(executingJobs.size());
        QuartzJob job;
        JobDetail jobDetail;
        JobKey jobKey;
        for (JobExecutionContext executingJob : executingJobs) {
            jobDetail = executingJob.getJobDetail();
            jobKey = jobDetail.getKey();
            job = createJob(jobKey.getName(), jobKey.getGroup(), scheduler, executingJob.getTrigger());
            jobList.add(job);
        }
        return jobList;
    }




    public  boolean isValidExpression(final String cronExpression) {
        CronTriggerImpl trigger = new CronTriggerImpl();
        try {
            trigger.setCronExpression(cronExpression);
            Date date = trigger.computeFirstFireTime(null);
            return date !=null && date.after(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /** * 添加任务 * * @param scheduleJob * @throws SchedulerException */
    public boolean addJob(QuartzJob job) throws SchedulerException {
        if(job == null || !QuartzJob.STATUS_RUNNING.equals(job.getJobStatus())) {
            return false;
        }

        String jobName = job.getJobName();
        String jobGroup = job.getJobGroup();
        if(!isValidExpression(job.getCronExpression())) {
            log.error("时间表达式错误（"+jobName+","+jobGroup+"）, "+job.getCronExpression());
            throw new InvalidParameterException("时间表达式错误（"+jobName+","+jobGroup+"）, "+job.getCronExpression());
        } else {
            // 任务名称和任务组设置规则： // 名称：task_1 .. // 组 ：group_1 ..
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName,  jobGroup);
            Trigger trigger = scheduler.getTrigger(triggerKey);
            // 不存在，创建一个
            if (null == trigger) {
                // 表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
                // 按新的表达式构建一个新的trigger
                trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
                        .startAt(job.getStartTime()==null ? (new Date()) : job.getStartTime()) // 设置job不早于这个时间进行运行,和调用trigger的setStartTime方法效果一致
                        .withSchedule(scheduleBuilder).build();

                JobDetail jobDetail = getJobDetail(job);
                // 将 job 信息存入数据库
                job.setStartTime(trigger.getStartTime());
                job.setNextTime(trigger.getNextFireTime());
                job.setPreviousTime(trigger.getPreviousFireTime());
                quartzJobService.insert(job);
                jobDetail.getJobDataMap().put(getJobIdentity(job), job);
                //因为上述的Trigger已经关联了JobDetail，可以使用  把trigger和jobDetail 加载到调度器上
                scheduler.scheduleJob(jobDetail, trigger);

            } else { // trigger已存在，则更新相应的定时设置
                // 更新 job 信息到数据库
                job.setStartTime(trigger.getStartTime());
                job.setNextTime(trigger.getNextFireTime());
                job.setPreviousTime(trigger.getPreviousFireTime());
                quartzJobService.insert(job);
                getJobDetail(job).getJobDataMap().put(getJobIdentity(job), job);

                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
                // 按新的表达式构建一个新的trigger
                trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
                        .startAt(job.getStartTime()==null ? (new Date()) : job.getStartTime()) // 设置job不早于这个时间进行运行,和调用trigger的setStartTime方法效果一致
                        .withSchedule(scheduleBuilder).build();
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        }
        // 启动
        if (!scheduler.isShutdown()) {
            scheduler.start();
        }
        return true;
    }

    private String getJobIdentity(QuartzJob job) {
        return "scheduleJob"+(job.getJobGroup() +"_"+job.getJobName());
    }

    private JobDetail getJobDetail(QuartzJob job) {
        Class clazz = null;
        try {
            clazz = Class.forName(job.getJobClass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //JobDetail jobDetail =
        JobBuilder jobBuilder = JobBuilder.newJob(clazz);
        resolveParameters(job,jobBuilder);
        JobDetail jobDetail = jobBuilder.withIdentity(job.getJobName(), job.getJobGroup()).storeDurably(true).build();
        return jobDetail;
    }

    public void resolveParameters(QuartzJob job,JobBuilder builder){
        List<JobParameter> parameters = job.getParameter();
        if (parameters!=null){
            for (JobParameter jobParameter : parameters) {
                String type = jobParameter.getType();
                String name = jobParameter.getName();
                String value = jobParameter.getValue();
                if (type.equals("int")) {
                    if (StringUtils.isEmpty(value)) {
                        builder.usingJobData(name, Integer.valueOf(0));
                    } else {
                        builder.usingJobData(name, Integer.parseInt(value));
                    }
                } else if (type.equals("long")) {
                    if (StringUtils.isEmpty(value)) {
                        builder.usingJobData(name, Integer.valueOf(0));
                    } else {
                        builder.usingJobData(name, Long.parseLong(value));
                    }
                } else if (type.equals("float")) {
                    if (StringUtils.isEmpty(value)) {
                        builder.usingJobData(name, 0.0D);
                    } else {
                        builder.usingJobData(name, Float.parseFloat(value));
                    }
                } else if (type.equals("boolean")) {
                    if (StringUtils.isEmpty(value)) {
                        builder.usingJobData(name, false);
                    } else {
                        builder.usingJobData(name, Boolean.parseBoolean(value));
                    }
                } else {
                    builder.usingJobData(name, value);
                }
            }
        }
    }


    /** * 暂停任务 * @param job * @return */
    @Transactional
    public boolean pauseJob(QuartzJob job){
        JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
        boolean result;
        try {
            scheduler.pauseJob(jobKey);

            // 更新任务状态到数据库
            job.setJobStatus(QuartzJob.STATUS_NOT_RUNNING);
            quartzJobService.update(job);
            result = true;
        } catch (SchedulerException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    /** * 恢复任务 * @param job * @return */
    @Transactional
    public boolean resumeJob(QuartzJob job){
        JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
        boolean result;
        try {
            log.info("resume job : " + (job.getJobGroup() + "_" + job.getJobName()));
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
                    .startAt(job.getStartTime()==null ? (new Date()) : job.getStartTime()) // 设置job不早于这个时间进行运行,和调用trigger的setStartTime方法效果一致
                    .withSchedule(scheduleBuilder).build();
            scheduler.rescheduleJob(triggerKey, trigger);
            scheduler.resumeJob(jobKey);

            // 更新任务状态到数据库
            job.setJobStatus(QuartzJob.STATUS_RUNNING);
            quartzJobService.update(job);

            result = true;
        } catch (SchedulerException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }


    /** * 删除任务 */
    @Transactional
    public boolean deleteJob(QuartzJob job){

        JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
        boolean result;
        try{
            //先暂停
            scheduler.pauseJob(jobKey);
            scheduler.deleteJob(jobKey);

            // 更新任务状态到数据库
            job.setJobStatus(QuartzJob.STATUS_DELETED);
            quartzJobService.update(job);

            result = true;
        } catch (SchedulerException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    /** * 立即执行一个任务 * @param scheduleJob * @throws SchedulerException */
    public void startJob(QuartzJob scheduleJob) throws SchedulerException{
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.triggerJob(jobKey);
    }

    /** * 更新任务时间表达式 * @param job * @throws SchedulerException */
    @Transactional
    public void updateCronExpression(QuartzJob job) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
        //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        //表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
        //按新的cronExpression表达式重新构建trigger
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        //按新的trigger重新设置job执行
        scheduler.rescheduleJob(triggerKey, trigger);

        // 更新 job 信息到数据库
        job.setStartTime(trigger.getStartTime());
        job.setNextTime(trigger.getNextFireTime());
        job.setPreviousTime(trigger.getPreviousFireTime());
        quartzJobService.update(job);
        getJobDetail(job).getJobDataMap().put(getJobIdentity(job), job);
    }

    /** 设置job的开始schedule时间 * @param job * @throws SchedulerException */
    @Transactional
    public void updateStartTime(QuartzJob job) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
        //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
        CronTriggerImpl trigger = (CronTriggerImpl) scheduler.getTrigger(triggerKey);
        trigger.setStartTime(job.getStartTime());
        //按新的trigger重新设置job执行
        scheduler.rescheduleJob(triggerKey, trigger);
        // 更新 job 信息到数据库
        job.setStartTime(trigger.getStartTime());
        job.setNextTime(trigger.getNextFireTime());
        job.setPreviousTime(trigger.getPreviousFireTime());
        quartzJobService.update(job);
        getJobDetail(job).getJobDataMap().put(getJobIdentity(job), job);
    }

    /**
     * 更改内容 1 更新cron 2更新执行类 3更新参数 4还有描述信息这些
     * @param job
     * @throws SchedulerException
     */
    @Transactional
    public void update(QuartzJob job)throws SchedulerException{
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        //表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
        JobDetail jobDetail = getJobDetail(job);
        jobDetail.getJobDataMap().put(getJobIdentity(job), job);
        //任务替换
        scheduler.addJob(jobDetail,true);
        //按新的cronExpression表达式重新构建trigger
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        //按新的trigger重新设置job执行
        scheduler.rescheduleJob(triggerKey, trigger);
        job.setStartTime(trigger.getStartTime());
        job.setNextTime(trigger.getNextFireTime());
        job.setPreviousTime(trigger.getPreviousFireTime());
        quartzJobService.update(job);
    }

}
