package com.ev.security;

import com.ev.common.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author
 * @create 2019-02-15 上午10:43
 **/
public class EvLogoutHander implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //返回信息还是跳转 清空SESSION什么的
        JsonResult result=JsonResult.ok("退出成功,请重新登录");
        ObjectMapper objectMapper=new ObjectMapper();
        String responseMessage=objectMapper.writeValueAsString(result);
        response.setContentType("application/json;chartset=UTF-8");
        response.getWriter().write(responseMessage);
    }
}
