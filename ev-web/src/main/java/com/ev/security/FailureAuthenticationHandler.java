package com.ev.security;

import com.ev.common.JsonResult;
import com.ev.utils.WebUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
*
 * @author
 * @create 2018-11-29 上午9:51
 *
*/

@Component
@Slf4j
public class FailureAuthenticationHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String message;
        if (exception instanceof UsernameNotFoundException) {
            message = "用户不存在！";
        } else if (exception instanceof BadCredentialsException) {
            message = "用户名或密码错误！";
        } else if (exception instanceof LockedException) {
            message = "用户已被锁定！";
        } else if (exception instanceof DisabledException) {
            message = "用户不可用！";
        } else if (exception instanceof AccountExpiredException) {
            message = "账户已过期！";
        } else if (exception instanceof CredentialsExpiredException) {
            message = "用户密码已过期！";
        } else if (exception instanceof SessionAuthenticationException){
            message="用户已登录,如账号异常请修改密码";
        } else {
            message = "认证失败，请联系网站管理员！";
        }
        if (WebUtil.isAjaxRequest(request)){
            response.setContentType("application/json;chartset=UTF-8");
            //这里可以判断是什么异常   比如验证码错误异常等等
            response.getWriter().write(objectMapper.writeValueAsString(JsonResult.error(message)));
        }else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
