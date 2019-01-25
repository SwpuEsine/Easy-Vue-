package com.puhui8.config;

import com.puhui8.security.ApiPermissionSecurityFilter;
import com.puhui8.security.SuccessAuthenticationHandler;
import com.puhui8.security.strategy.DefaultInvalidSessionStrategy;
import com.puhui8.security.strategy.DefaultSessionExpiredStrategy;
import com.puhui8.service.impl.EvUserDetailServcie;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsUtils;

/**
 * spring security 核心配置 也就是权限管理   认证 授权 攻击防护
 * @author
 * @create 2018-11-28 上午11:32
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${security.login.page}")
    private String loginUrl;

    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    EvUserDetailServcie userDetailsService;

  /*  @Autowired
    PersistentTokenRepository persistentTokenRepository;*/


    //权限不够的异常处理器
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;


    //自定义拦截器
    @Autowired
    private ApiPermissionSecurityFilter securityFilter;

    /**
     * 过滤器默认处理的登录请求是/login post形式
     * @param http
     * @throws Exception
     *
     *
     * .exceptionHandling().accessDeniedHandler(accessDeniedHandler).
    and()
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
                String[] anonResourcesUrl = StringUtils.splitByWholeSeparatorPreserveAllTokens("/css/**,/js/**,/fonts/**,/img/**",",");
                //.userDetailsService(userDetailsService)  用这个tokenRepository去获取用户信息 获取后然后使用userDetailService去登录成功后显示界面
                //
                 http.userDetailsService(userDetailsService).addFilterBefore(securityFilter,FilterSecurityInterceptor.class).
                         formLogin()//表单认证
                .loginPage("/authentication/require")//登录页面 自定义页面(参考/authentication/require) 这里可以根据是html页面请求还是rest请求来返回不同的信息
                .loginProcessingUrl("/user/login")//处理登录请求的页面过滤器 默认处理的登录请求是/login post形式 指的是表单提交的地址
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                         //自己失效的策略
                         //.and().sessionManagement().invalidSessionStrategy(new DefaultInvalidSessionStrategy())
                         //被踢出去的策略
                         //.maximumSessions(1).expiredSessionStrategy(new DefaultSessionExpiredStrategy()).and()
                         .and().authorizeRequests()
                        /*
                .and().rememberMe().tokenRepository(persistentTokenRepository)
                .tokenValiditySeconds(500000).userDetailsService(userDetailsService)*/

                          .antMatchers(anonResourcesUrl).permitAll()
                         .antMatchers("/","/authentication/require","/image/code","/user/regist","/session/timeout","/ll.html","/login.html").permitAll()
                         //这句话一定要放在最后 不然就gg了
                         .anyRequest().authenticated()
                             //任何请求
                 //都需要身份认证
                .and()
                .csrf().disable();

    }
}
