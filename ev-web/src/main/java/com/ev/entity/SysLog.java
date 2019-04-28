package com.ev.entity;


import lombok.Data;

import java.util.Date;

@Data
public class SysLog {
    private String sysLogId;
    private String userName;
    private String className;
    private String methodName;
    private String  parameters;
    private String url;
    private Date createTime;
    private Long  duration;//请求耗时  毫秒
    private Boolean isException;//是否异常
    private Boolean isProcess;//异常是否处理
    private String ipAddress;//请求ip
}
