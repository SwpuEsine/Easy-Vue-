//package com.ev.security;
//
//import com.ev.entity.SysRole;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.access.SecurityConfig;
//import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//
///**
// * 菜单权限数据源
// *
// * @author
// * @create 2019-01-22 下午5:44
// **/
//public class ApiFilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
//
//    private List<SysRole> getAuthoritiesRolesByUrl(String url){
//        SysRole sysRole = new SysRole();
//        sysRole.setRemark("管理员");
//        sysRole.setRoleId(1L);
//        sysRole.setRoleName("admin");
//        return Arrays.asList(sysRole);
//    }
//
//    private List<SysRole> setUrlRoleMapping(){
//        SysRole sysRole = new SysRole();
//        sysRole.setRemark("管理员");
//        sysRole.setRoleId(1L);
//        sysRole.setRoleName("admin");
//        return Arrays.asList(sysRole);
//    }
//
//    /**
//     *  此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，
//     // 我准备直接拦截，不管请求的url 是什么都直接拦截，然后在AccessDecisionManager的decide
//     方 法中做拦截还是放行的决策。 开始是想做一个url->角色 的映射 在securityFilter里面就可以拦截到能访问此url需要的角色
//        后来感觉不太好 所以在decisionManager里面去判断 做角色->url的映射 为了做成动态权限 设置权限后不需要重新登录 马上起作用
//     //所以此方法的返回值不能返回 null 此处我就随便返回一下。
//     * @param object
//     * @return
//     * @throws IllegalArgumentException
//     */
//
//    @Override
//    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
//        /*FilterInvocation fi = (FilterInvocation) object;
//        //根据url获取需要的角色
//        List<SysRole> neededRoles = getAuthoritiesRolesByUrl(fi.getRequestUrl());
//        if (neededRoles != null) {
//            return SecurityConfig.createList(neededRoles.stream().map(role -> role.getRoleName()).collect(Collectors.toList()).toArray(new String[]{}));
//        }
//        //  返回默认配置
//        return null;*/
//        Collection<ConfigAttribute> co = new ArrayList<>();
//        co.add(new SecurityConfig("all"));
//        return co;
//    }
//
//    @Override
//    public Collection<ConfigAttribute> getAllConfigAttributes() {
//        //拦截器会在启动的时候调用这个方法  用redis存储 url和角色的映射关系
//        return null;
//        /*setUrlRoleMapping();
//        return new ArrayList<ConfigAttribute>();*/
//    }
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        //关键  一定要放回true
//        return true;
//    }
//
//
//}
