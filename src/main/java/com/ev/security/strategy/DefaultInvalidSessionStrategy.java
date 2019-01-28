//package com.ev.security.strategy;
//
//import com.ev.common.JsonResult;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.web.session.InvalidSessionStrategy;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 失效
// *
// * @author
// * @create 2019-01-24 下午12:11
// **/
//public class DefaultInvalidSessionStrategy implements InvalidSessionStrategy {
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Override
//    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        if (request.getHeader("content-type").equals("application/json")|| StringUtils.contains(request.getRequestURL().toString(),"/user/login")){
//            response.setContentType("application/json;chartset=UTF-8");
//            response.getWriter().write(objectMapper.writeValueAsString(JsonResult.error("session失效")));
//        }else {
//            //默认跳转
//            response.sendRedirect("/login");
//        }
//    }
//}
