package com.ev.security.authorize.provider;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author
 * @create 2019-02-15 下午3:52
 **/
//匿名可以访问的URL
@Component
@Order(value = Integer.MIN_VALUE)
public class AnonResourcesConfigProvider implements AuthorizeConfigProvider {

    //HTML登录页面
    @Value("${security.login.page}")
    private String loginUrl;
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        String[] anonResourcesUrl = StringUtils.splitByWholeSeparatorPreserveAllTokens("/css/**,/js/**,/fonts/**,/img/**",",");
        config.antMatchers(anonResourcesUrl).permitAll()
                .antMatchers("/authentication/require",loginUrl,"/session/invalid","/logout","/favicon.ico").permitAll()
        .antMatchers("/role/**","/menu/info","/user/info","/menu/selectList","/user/menuList","/druid/**").permitAll()
                .antMatchers("/plan/info").permitAll();
    }
}
