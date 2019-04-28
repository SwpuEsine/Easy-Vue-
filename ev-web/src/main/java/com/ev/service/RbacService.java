package com.ev.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author
 * @create 2019-02-15 上午11:51
 **/
public interface RbacService {
    //数据库权限控制
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
