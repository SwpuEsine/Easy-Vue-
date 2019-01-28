//package com.ev.security;
//
//import com.ev.entity.SysUser;
//import org.apache.commons.lang.BooleanUtils;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//
//import java.util.Collection;
//
///**
// * springSecurity使用累
// *
// * @author
// * @create 2019-01-22 上午9:37
// **/
//
//public class SysUserDetails extends User {
//
//    private SysUser sysUser;
//
//    public SysUserDetails(SysUser sysUser,Collection<? extends GrantedAuthority> authorities){
//        this(sysUser.getUserName(),sysUser.getPassWord(),authorities);
//        this.sysUser=sysUser;
//    }
//
//    public SysUserDetails(String username, String password,Collection<? extends GrantedAuthority> authorities){
//        super(username,password,authorities);
//    }
//
//    public SysUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//    }
//
//
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return  BooleanUtils.toBoolean(sysUser.getIsLock(),0,1);
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return BooleanUtils.toBoolean(sysUser.getIsExpire(),0,1);
//    }
//
//}
