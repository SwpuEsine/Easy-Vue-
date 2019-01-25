package com.puhui8.config;


import com.puhui8.security.ApiAccessDecisionManager;
import com.puhui8.security.ApiFilterSecurityMetadataSource;
import com.puhui8.security.AuthenticationAccessDeniedHandler;
import com.puhui8.security.Md5PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/***
 * 相当于XML配置
 *
 * @author
 * @create 2018-11-27 下午5:53
 *
 *
 */


@Configuration
public class WebConfig {
    //拦截器 先走pre 然后走post   不管是否抛出异常都会抛出  AFTERCOMPE
    /*@Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean registrationBean=new FilterRegistrationBean();
        registrationBean.setName("timeFilter");
        TimeFilter timeFilter=new TimeFilter();
        registrationBean.setFilter(timeFilter);
        registrationBean.setOrder(1);
        List<String> urlList=new ArrayList<String>();
        urlList.add("/*");
        registrationBean.setUrlPatterns(urlList);
        return registrationBean;
    }*/

    @Bean
    public PasswordEncoder passwordEncoder(){
        //这个BcryptPasswordEncoder 存储非常的安全
        return new BCryptPasswordEncoder();
        //return new Md5PasswordEncoder();

    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AuthenticationAccessDeniedHandler();
    }


    @Bean
    public ApiAccessDecisionManager apiAccessDecisionManager(){
        return new ApiAccessDecisionManager();
    }
    @Bean
    public ApiFilterSecurityMetadataSource apiFilterSecurityMetadataSource(){
        return new ApiFilterSecurityMetadataSource();
    }
    /*@Autowired
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository=new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //启动创建一张表
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;

    }*/

/*    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:8090");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;

    }*/

}
