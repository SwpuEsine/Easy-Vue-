package com.ev.controller;

import com.ev.common.SessionInterface;
import com.ev.entity.SysUser;

import javax.servlet.http.HttpSession;

/**
 * @author
 * @create 2019-02-18 上午10:56
 **/
public class BaseController {

    protected SysUser getCurrentUser(HttpSession session){
        return (SysUser) session.getAttribute(SessionInterface.SESSION_USER);
    }

    protected String  getUserPermission(HttpSession session){

        return (String) session.getAttribute(SessionInterface.USER_ROLE);
    }

}
