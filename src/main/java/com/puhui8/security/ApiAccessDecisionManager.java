package com.puhui8.security;

import com.puhui8.entity.SysRole;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 判断是否有权限
 *
 * @author
 * @create 2019-01-22 下午6:33
 **/
public class ApiAccessDecisionManager implements AccessDecisionManager {
    private List<RequestMatcher> getAntMathcers(List<String> urls){
        List<RequestMatcher> matchers = new ArrayList();
        for (String url:urls) {
            matchers.add(new AntPathRequestMatcher(url, null));
        }
        return matchers;
    }
    private List<String> getAuthoritiesUrlByRole(String ...role){
        return Arrays.asList("/image/code","/user/login");
    }
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        System.out.println("Enter self AccessDecisionManager");
        System.out.println("authentication"+authentication.toString());
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        System.out.println("请求路径:"+request.getRequestURL());
        System.out.println("请求方法:"+request.getMethod());
        String url, method;
        AntPathRequestMatcher matcher;

        for (GrantedAuthority ga : authentication.getAuthorities()) {
            System.out.println(ga.getAuthority());
            if (ga.getAuthority().equals("ROLE_ANONYMOUS")) {//未登录访问的页面
                //全部允许访问  未登录可访问url由配置文件读取
                return;
               /* for (RequestMatcher requestMatcher:getAntMathcers(Arrays.asList("/login","/image/code","/user/regist","/authentication/require","/user/login"))) {
                    if (requestMatcher.matches(request)) {
                        return;
                    }
                }*/
            }else {
                List<String> urls = getAuthoritiesUrlByRole(ga.getAuthority());
                for (RequestMatcher requestMatcher:getAntMathcers(urls)) {
                    if (requestMatcher.matches(request)) {
                        return;
                    }
                }
            }
        }

        //
        throw new AccessDeniedException("用户无权限访问");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
