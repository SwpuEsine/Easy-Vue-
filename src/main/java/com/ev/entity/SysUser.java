package com.ev.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * @create 2019-01-21 下午3:05
 **/
@Data
public class SysUser implements Serializable{
    private Long userId;
    @NotNull(message = "用户名不能为空")
    private String userName;
    @NotNull(message = "密码不能为空")
    private String passWord;
    private Short isLock;
    private Date  createTime;
    private Date  updateTime;//上次操作事件
    private Short errorTime;//登录错误次数
    private String phone;
    private String email;
    private Short isExpire;
    private String avator;//用户头像

}
