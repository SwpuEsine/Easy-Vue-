package com.ev.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

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

    @Value("${spring.application.name}")
    private String applicationName;
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

    /**
     * 自定义密码加密规则
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
        //return new Md5PasswordEncoder();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    /**
     * 当SESSION销毁时候 通知SESSION注册  否则SESSION并发会失效
     * @return
     */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    /**
     * cookie实现spring session
     * @return
     */
   /* @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();

        //cookie名字
        defaultCookieSerializer.setCookieName(applicationName+":sessionId");

        defaultCookieSerializer.setUseHttpOnlyCookie(true);
        //域
        //defaultCookieSerializer.setDomainName("xxx.com");
        //存储路径
        defaultCookieSerializer.setCookiePath("/");
        return defaultCookieSerializer;

    }*/


    /*@Bean
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
