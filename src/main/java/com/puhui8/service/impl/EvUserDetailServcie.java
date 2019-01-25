package com.puhui8.service.impl;

import com.puhui8.entity.SysUser;
import com.puhui8.security.SysUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author
 * @create 2018-12-19 上午9:17
 **/
@Service
@Slf4j
public class EvUserDetailServcie implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**
         * 这里去读取数据库信息
         */
        log.info("userName:"+username);
        SysUser sysUser = sysUserService.findByUserName(username);
        if (sysUser!=null){
            //获取用户角色列表
            String permissions = this.sysUserService.getUserRoleNameWithDelimiter(sysUser.getUserId());
            SysUserDetails sysUserDetails=new SysUserDetails(sysUser,AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
            return sysUserDetails;
        }else {
            throw new UsernameNotFoundException("用户不存在");
        }

    }
}
