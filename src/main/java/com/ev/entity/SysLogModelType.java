package com.ev.entity;

/**
 * @author
 * @create 2019-01-27 上午11:27
 **/
public enum  SysLogModelType {
    USER("用户管理"),
    NULL("未定义"),
    DEPARTMENT("部门管理");
    private final String type;

    SysLogModelType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
