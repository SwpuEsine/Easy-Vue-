//package com.ev.security;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ev.common.JsonResult;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
//
//@Slf4j
//@Component
//public class SuccessAuthenticationHandler extends SavedRequestAwareAuthenticationSuccessHandler {
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
//        //authentication  封装了信息  ip 什么的
//        //log.info("登录成功");
//        logger.info("登录成功:"+request.getRequestURL());
//        if (request.getHeader("content-type").equals("application/json")|| StringUtils.contains(request.getRequestURL().toString(),"/user/login")){
//            //登录成功   用户session存储到redis
//            response.setContentType("application/json;chartset=UTF-8");
//            response.getWriter().write(objectMapper.writeValueAsString(JsonResult.ok("登录成功")));
//        }else {
//            //默认跳转
//            super.onAuthenticationSuccess(request, response, authentication);
//        }
//
//    }
//}
