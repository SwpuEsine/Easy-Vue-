package com.ev.entity;


import lombok.Data;

import java.util.Date;

@Data
public class SysLog {
    private Long sysLogId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getSysLogId() {
        return sysLogId;
    }

    public void setSysLogId(Long sysLogId) {
        this.sysLogId = sysLogId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Boolean getException() {
        return isException;
    }

    public void setException(Boolean exception) {
        isException = exception;
    }

    public Boolean getProcess() {
        return isProcess;
    }

    public void setProcess(Boolean process) {
        isProcess = process;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
