package com.ev.service.impl;

import com.ev.common.SecurityPrefix;
import com.ev.service.RbacService;
import com.ev.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * @author
 * @create 2019-02-15 上午11:52
 **/
@Service("rbacService")
public class RbacServiceImpl implements RbacService{

    //如果用户数据库存储/user/*  传过来的是/user/1 则这个可以匹配
    private AntPathMatcher antPathMatcher=new AntPathMatcher();

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal=authentication.getPrincipal();
        boolean haspermission  =false;
        //principal 可能是一个字符串  匿名用户
        if (principal instanceof UserDetails){
            String username = ((UserDetails) principal).getUsername();
            Collection<? extends GrantedAuthority> authorities = ((UserDetails) principal).getAuthorities();
            //判断该角色是否拥有访问路径的权限  路径比对
            for (GrantedAuthority authority:authorities) {
                //用户角色可以访问的路径
                List<String> paths = redisUtil.lRange(SecurityPrefix.AUTHORTITY_PREFIX+authority.getAuthority(), 0, -1);
                for (String path:paths) {
                    if (antPathMatcher.match(path,request.getRequestURI())){
                        haspermission=true;
                        break;
                    }
                }
                if (haspermission==true){
                    break;
                }
            }
        }
        return haspermission;
    }
}
