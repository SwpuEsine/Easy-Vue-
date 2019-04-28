package com.ev.entity;

import lombok.Data;

import java.util.Date;

/**
 * 系统执行日志记录
 *
 * @author
 * @create 2019-03-06 下午5:26
 **/
@Data
public class SysJobLog {
    protected Long logId;
    protected String jobName;
    protected String trigName;
    protected Date startTime;
    protected Date endTime;
    protected String content;
    protected int state;
    protected Long runTime;
}
