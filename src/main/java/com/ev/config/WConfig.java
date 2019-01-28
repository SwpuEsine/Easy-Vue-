//package com.ev.config;
//
//import com.ev.security.ApiPermissionSecurityFilter;
//import com.ev.service.impl.EvUserDetailServcie;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
///**
// * spring security 核心配置 也就是权限管理   认证 授权 攻击防护
// * @author
// * @create 2018-11-28 上午11:32
// **/
//@Configuration
//@EnableWebSecurity
//public class WConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//                    http.
//                         formLogin()//表单认证
//
//                .and()
//                .csrf().disable();
//
//    }
//}
