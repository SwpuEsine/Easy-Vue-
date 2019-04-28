package com.ev.config;

import com.ev.security.EvLogoutHander;
import com.ev.security.authorize.manager.AuthorizeConfigManager;
import com.ev.security.strategy.SessionExpireStrategy;
import com.ev.service.impl.EvUserDetailServcie;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 配置文件
*
 * @author
 * @create 2019-02-10 上午10:58
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    //自定义登录成功处理器
    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;
    //失败处理器
    @Autowired
    AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    EvUserDetailServcie userDetailsService;

    @Autowired
    AuthorizeConfigManager authorizeConfigManager;

    @Autowired
    SessionRegistry sessionRegistry;
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.headers().frameOptions().disable().and().//解决iframe 同源问题
                 formLogin().
                 loginPage("/authentication/require").   //处理登录的controller地址
                 loginProcessingUrl("/user/login").  //表单提交的POST地址
                 successHandler(authenticationSuccessHandler). //自定义成功登录逻辑 是跳转还是JSON
                 failureHandler(authenticationFailureHandler).//自定义失败处理逻辑
                 and()  //单用户
                 .userDetailsService(userDetailsService)
                 .sessionManagement().invalidSessionUrl("/session/invalid").//session过期跳转
                 maximumSessions(7) //后者登录踢出前者 然后前者页面显示为异常信息 需要重新登录（将前者session失效）
                 .maxSessionsPreventsLogin(true)//阻止后者登录 //这里在浏览器关闭事件中 要调用SESSION.INVALIDE 否则有BUG
                .expiredSessionStrategy(new SessionExpireStrategy())//session 并发登录返回一个提示信息 不是直接跳转
                .sessionRegistry(sessionRegistry) //这里要这么写 http://group.jobbole.com/24133/ 否则并发登录不会删除SESSION   详情 https://blog.csdn.net/baidu_27989705/article/details/76673632
                .and()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessHandler(new EvLogoutHander())
                .deleteCookies("JESSIONID").invalidateHttpSession(true)
                .and()
                .csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());

    }
}
