package com.ev.security.authorize.provider;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author
 * @create 2019-02-15 下午4:04
 **/
//需要权限的资源
@Component
@Order(value = Integer.MAX_VALUE)
public class AuthorizeResourcesConfigProvider implements AuthorizeConfigProvider{

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.anyRequest().access("@rbacService.hasPermission(request,authentication)");
    }
}
