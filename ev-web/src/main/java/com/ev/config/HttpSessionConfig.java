package com.ev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;

/**
 * @author
 * @create 2019-02-16 下午3:43
 **/
//设置session失效时间为30分钟
/*@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 1800)
public class HttpSessionConfig {
    @Bean
    public HeaderHttpSessionIdResolver httpSessionStrategy() {
        return new HeaderHttpSessionIdResolver("Token");
    }
}*/
