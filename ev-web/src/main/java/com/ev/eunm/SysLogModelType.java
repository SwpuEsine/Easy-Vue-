package com.ev.eunm;

/**
 * @author
 * @create 2019-01-27 上午11:27
 **/
public enum  SysLogModelType {
    USER("用户管理"),
    NULL("未定义"),
    DEPARTMENT("部门管理"),
    MENU("菜单管理"),
    LOG("日志管理"),
    ROLE("角色管理");

    private final String type;

    SysLogModelType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
