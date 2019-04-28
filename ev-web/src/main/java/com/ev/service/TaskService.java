package com.ev.service;

import com.ev.entity.QuartzJob;
import org.quartz.SchedulerException;

import java.util.List;


public interface TaskService {
    public QuartzJob getJob(String jobName, String jobGroup) throws SchedulerException;
    public List<QuartzJob> getAllJobs() throws SchedulerException;
    public List<QuartzJob> getRunningJob() throws SchedulerException;
    public void startJob(QuartzJob scheduleJob) throws SchedulerException;
    public void updateCronExpression(QuartzJob job) throws SchedulerException;
    public void updateStartTime(QuartzJob job) throws SchedulerException;
    public boolean addJob(QuartzJob job) throws SchedulerException;
    public boolean resumeJob(QuartzJob job);
    public boolean pauseJob(QuartzJob job);
    public boolean deleteJob(QuartzJob job);
    void update(QuartzJob quartzJob) throws SchedulerException;
}
