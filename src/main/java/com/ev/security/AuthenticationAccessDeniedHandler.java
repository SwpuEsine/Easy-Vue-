//package com.ev.security;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ev.common.JsonResult;
//import com.ev.utils.WebUtil;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.web.DefaultRedirectStrategy;
//import org.springframework.security.web.RedirectStrategy;
//import org.springframework.security.web.access.AccessDeniedHandler;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {
//
//    private ObjectMapper mapper = new ObjectMapper();
//
//    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//
//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
//
//        if (WebUtil.isAjaxRequest(request)) {
//            response.setContentType( "application/json;charset=utf-8");
//            response.getWriter().write(this.mapper.writeValueAsString(JsonResult.error("权限不足")));
//        } else {
//            redirectStrategy.sendRedirect(request, response, "/access/deny/403");
//        }
//
//    }
//}
