package com.ev.security.authorize.manager;

import com.ev.security.authorize.provider.AuthorizeConfigProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author
 * @create 2019-02-15 下午3:57
 **/
@Component
public class EvAuthorizeConfigManager  implements AuthorizeConfigManager{
    @Autowired
    private List<AuthorizeConfigProvider> providers;
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        for (AuthorizeConfigProvider provider:providers) {
            provider.config(config);
        }
        //config.anyRequest().authenticated();
    }
}
