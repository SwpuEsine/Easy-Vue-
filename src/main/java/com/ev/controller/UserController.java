/*
package com.ev.controller;

import com.ev.common.JsonResult;
import com.ev.entity.SysUser;
import com.ev.service.impl.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;

*/
/**
 * @author
 * @create 2018-11-29 上午10:23
 **//*

@RestController
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping(value = "/userdetail")
    public Object getUser(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }

    @RequestMapping("/assets/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {

    }


    @GetMapping("/login")
    public JsonResult loginPage(HttpServletRequest request) {
        return JsonResult.ok("兄弟需要登录啊");
    }

    @PostMapping("user/regist")
    public JsonResult register(@Valid SysUser sysUser, BindingResult errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            //自定义的返回，并将错误信息返回
            return JsonResult.error(message);
        }
        String password=passwordEncoder.encode(sysUser.getPassWord());
        sysUser.setPassWord(password);
        sysUser.setIsLock((short)0);
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        sysUser.setIsExpire((short)0);
        sysUserService.insert(sysUser);
        return JsonResult.ok("注册成功");
    }
}
*/
