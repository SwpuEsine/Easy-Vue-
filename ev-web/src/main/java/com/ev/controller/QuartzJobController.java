package com.ev.controller;

import com.ev.common.JsonResult;
import com.ev.common.PageList;
import com.ev.entity.QuartzJob;
import com.ev.scheduler.JobParameter;
import com.ev.service.QuartzJobService;
import com.ev.service.TaskService;
import com.ev.vo.RequestParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author
 * @create 2019-03-11 下午4:38
 **/
@RestController
@RequestMapping(value = "/plan")
@Slf4j
public class QuartzJobController {

    @Autowired
    private QuartzJobService jobService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/list")
    public PageList<QuartzJob> getJobList(RequestParam requestParam) {
        PageList<QuartzJob> jobList = jobService.findByJobStatusNot(requestParam.getPage(),requestParam.getLimit(),QuartzJob.STATUS_DELETED);
        return jobList;
    }


    @GetMapping("/info")
    public JsonResult getJobInfo(Long jobId) {
        QuartzJob job = jobService.findByJobId(jobId);
        return JsonResult.ok(job);
    }
    /**
     * 恢复任务
     * @param quartzJob
     * @return
     * @throws SchedulerException
     */
    @PostMapping(value = "/resume")
    public JsonResult resumeJob(@RequestBody QuartzJob quartzJob) throws SchedulerException {
        if (StringUtils.isEmpty(quartzJob.getJobName())){
            return JsonResult.error("jobName不能为空");
        }
        if (StringUtils.isEmpty(quartzJob.getJobGroup())){
            return JsonResult.error("groupName不能为空");
        }
        taskService.resumeJob(quartzJob);
        return JsonResult.ok("重启成功");
    }

    /**
     * 立即执行一次
     * @param quartzJob
     * @return
     * @throws SchedulerException
     */
    @PostMapping(value = "/execute")
    public JsonResult execute(@RequestBody QuartzJob quartzJob) throws SchedulerException {
        if (StringUtils.isEmpty(quartzJob.getJobName())){
            return JsonResult.error("jobName不能为空");
        }
        if (StringUtils.isEmpty(quartzJob.getJobGroup())){
            return JsonResult.error("groupName不能为空");
        }
        taskService.startJob(quartzJob);
        return JsonResult.ok("执行成功");
    }

    @PostMapping(value = "/pause")
    public JsonResult pause(@RequestBody QuartzJob quartzJob) throws SchedulerException {
        if (StringUtils.isEmpty(quartzJob.getJobName())){
            return JsonResult.error("jobName不能为空");
        }
        if (StringUtils.isEmpty(quartzJob.getJobGroup())){
            return JsonResult.error("groupName不能为空");
        }
        taskService.pauseJob(quartzJob);
        return JsonResult.ok("暂停成功");
    }


    @PostMapping(value = "/edit")
    public JsonResult editJob(@RequestBody QuartzJob quartzJob) throws SchedulerException {
        if (StringUtils.isEmpty(quartzJob.getJobName())){
            return JsonResult.error("jobName不能为空");
        }
        if (StringUtils.isEmpty(quartzJob.getJobGroup())){
            return JsonResult.error("groupName不能为空");
        }
        if (StringUtils.isEmpty(quartzJob.getCronExpression())){
            return JsonResult.error("cron表达式不能为空");
        }
        taskService.update(quartzJob);
        return JsonResult.ok("修改成功");
    }


    @PostMapping(value = "/add")
    public JsonResult addJob(@RequestBody QuartzJob quartzJob) throws SchedulerException {
        if (StringUtils.isEmpty(quartzJob.getJobName())){
            return JsonResult.error("jobName不能为空");
        }
        if (StringUtils.isEmpty(quartzJob.getJobGroup())){
            return JsonResult.error("groupName不能为空");
        }
        if (StringUtils.isEmpty(quartzJob.getCronExpression())){
            return JsonResult.error("cron表达式不能为空");
        }
        taskService.addJob(quartzJob);
        return JsonResult.ok("添加成功");
    }



    @PostMapping(value = "/delete")
    public JsonResult delete(@RequestBody QuartzJob quartzJob){
        if (StringUtils.isEmpty(quartzJob.getJobName())){
            return JsonResult.error("jobName不能为空");
        }
        if (StringUtils.isEmpty(quartzJob.getJobGroup())){
            return JsonResult.error("groupName不能为空");
        }
        boolean b = taskService.deleteJob(quartzJob);
        if (b){
           return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

}
