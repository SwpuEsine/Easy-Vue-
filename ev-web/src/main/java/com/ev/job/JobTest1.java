package com.ev.job;

import com.ev.scheduler.AbstractBaseJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;

/**
 * @author
 * @create 2019-03-11 下午3:08
 **/
@Slf4j
public class JobTest1 extends AbstractBaseJob{
    @Override
    public void executeJob(JobExecutionContext context) throws Exception {
        log.info("我是测试的++++++++++++");
    }
}
