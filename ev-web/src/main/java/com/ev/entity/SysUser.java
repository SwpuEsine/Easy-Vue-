package com.ev.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * @create 2019-01-21 下午3:05
 **/

public class SysUser implements Serializable{
    private Long userId;
    @NotNull(message = "用户名不能为空")
    private String userName;
    @NotNull(message = "密码不能为空")
    private String passWord;
    private String nikeName;
    private Short sex;
    private Short isLock;
    private Date  createTime;
    private Date  updateTime;//上次操作事件
    private Short errorTime;//登录错误次数
    private String phone;
    private String email;
    private Short isExpire;
    private String avator;//用户头像

    @Override
    public String toString() {
        return "SysUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", nikeName='" + nikeName + '\'' +
                ", sex=" + sex +
                ", isLock=" + isLock +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", errorTime=" + errorTime +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", isExpire=" + isExpire +
                ", avator='" + avator + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysUser sysUser = (SysUser) o;

        return userName != null ? userName.equals(sysUser.userName) : sysUser.userName == null;
    }

    @Override
    public int hashCode() {
        return userName != null ? userName.hashCode() : 0;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Short getIsLock() {
        return isLock;
    }

    public void setIsLock(Short isLock) {
        this.isLock = isLock;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Short getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(Short errorTime) {
        this.errorTime = errorTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Short getIsExpire() {
        return isExpire;
    }

    public void setIsExpire(Short isExpire) {
        this.isExpire = isExpire;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

}
