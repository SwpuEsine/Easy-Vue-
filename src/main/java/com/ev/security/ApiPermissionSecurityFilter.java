/*
package com.ev.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import java.io.IOException;

*/
/**
 * url拦截器
 *
 * @author
 * @create 2019-01-23 上午9:10
 **//*


public class ApiPermissionSecurityFilter extends AbstractSecurityInterceptor implements Filter {

    */
/*@Autowired
    private ApiFilterSecurityMetadataSource metadataSource;

    @Autowired
    private ApiAccessDecisionManager apiAccessDecisionManager;
*//*



    @PostConstruct
    public void init(){
        //super.setAuthenticationManager(authenticationManager);
        super.setAccessDecisionManager(apiAccessDecisionManager);
    }

    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException{
        FilterInvocation fi = new FilterInvocation( request, response, chain );
        invoke(fi);
    }

    public Class<? extends Object> getSecureObjectClass(){
        return FilterInvocation.class;
    }

    public void invoke( FilterInvocation fi ) throws IOException, ServletException{
        //fi里面有一个被拦截的url
        //里面调用MetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
        //再调用DecisionManager的decide方法来校验用户的权限是否足够


        InterceptorStatusToken token = super.beforeInvocation(fi);
        try{
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        }finally{
            super.afterInvocation(token, null);
        }
    }


    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource(){
        return this.metadataSource;
    }
    public void destroy(){
    }
    public void init( FilterConfig filterconfig ) throws ServletException{
    }

}
*/
