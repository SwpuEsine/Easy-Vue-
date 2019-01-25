package com.puhui8.entity;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 用户
 *
 * @author
 * @create 2018-11-27 下午4:38
 **/

public class User {
    public  interface  UserSimpleView{};
    public interface  UserDetailView extends  UserSimpleView{};

    @NotNull(message = "用户名不能为空")
    private String userName;
    @ApiModelProperty(value = "密码")
    private String passWord;

    @JsonView(UserSimpleView.class)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @JsonView(UserDetailView.class)
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
