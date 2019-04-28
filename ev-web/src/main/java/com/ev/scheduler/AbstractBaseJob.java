package com.ev.scheduler;


import com.ev.entity.QuartzJob;
import com.ev.entity.SysJobLog;
import com.ev.listener.SpringContextUtil;
import com.ev.service.QuartzJobService;
import com.ev.service.SysJobLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * @author
 * @create 2019-03-07 下午12:56
 **/
@Slf4j
public abstract class AbstractBaseJob implements Job {

    /**
     * 注意这里要自己转化下  因为不是同一个类加载器加载的 所以不能强转  否则报错
     * @param context
     * @throws Exception
     */
    public abstract void executeJob(JobExecutionContext context)throws Exception;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail job = context.getJobDetail();
        JobKey key = job.getKey();
        String jobIdentity = "scheduleJob" + key.getGroup() + "_" + key.getName();
        Trigger trigger = context.getTrigger();
        String triggerName = trigger.getKey().getName();
        //不能转化 类加载器不一致也不可以转化
        QuartzJob scheduleJob=new QuartzJob();
        Object o = context.getMergedJobDataMap().get(jobIdentity);
        try {
            BeanUtils.copyProperties(scheduleJob,o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        log.info("运行任务名称 = [" + scheduleJob + "]");

        long begin = System.currentTimeMillis();
        SysJobLog sysJobLog=new SysJobLog();
        sysJobLog.setStartTime(new Date());
        String content="任务执行成功";
        int status=1;
        try {
            this.executeJob(context);
            scheduleJob.setNextTime(trigger.getNextFireTime());
            scheduleJob.setPreviousTime(trigger.getPreviousFireTime());
            QuartzJobService jobService = (QuartzJobService) SpringContextUtil.getBean("quartzJobService");
            jobService.update(scheduleJob);

        } catch (Exception e) {
             content = e.toString();
             status=0;
             e.printStackTrace();
        }finally {
            SysJobLogService sysJobLogService= (SysJobLogService) SpringContextUtil.getBean("sysJobLogService");
            long end=System.currentTimeMillis();
            long consumeSeconds = (end - begin) / 1000L;
            sysJobLog.setEndTime(new Date());
            sysJobLog.setContent(content);
            sysJobLog.setJobName(jobIdentity);
            sysJobLog.setRunTime(consumeSeconds);
            sysJobLog.setState(status);
            sysJobLog.setTrigName(triggerName);
            sysJobLogService.insertSelective(sysJobLog);
        }
    }
}
