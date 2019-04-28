package com.ev.security;

import com.ev.common.JsonResult;
import com.ev.common.SessionInterface;
import com.ev.entity.SysUser;
import com.ev.service.SysUserService;
import com.ev.utils.WebUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Slf4j
@Component
public class SuccessAuthenticationHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private SysUserService sysUserService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        //authentication  封装了信息  ip 什么的
        //log.info("登录成功");
        setCurrentUserInfo(request.getSession());
        logger.info("登录成功:"+request.getRequestURL());
        if (WebUtil.isAjaxRequest(request)){
            //登录成功   用户session存储到redis
            response.setContentType("application/json;chartset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(JsonResult.ok("登录成功")));
        }else{
            //默认跳转
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }
    /**
     * 登录成功存入用户信息
     */
    private void setCurrentUserInfo(HttpSession session){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        SysUser user = new SysUser();
        if(principal instanceof SysUserDetails){
            SysUserDetails userDetails = (SysUserDetails) principal;
            String username = userDetails.getUsername();
            SysUser sysUser = sysUserService.findByUserName(username);
            if (sysUser!=null){
                session.setAttribute(SessionInterface.SESSION_USER,sysUser);
            }
            String permissions = this.sysUserService.getUserRoleNameWithDelimiter(sysUser.getUserId());
            session.setAttribute(SessionInterface.USER_ROLE,permissions);

        }
    }
}
