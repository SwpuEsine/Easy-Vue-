package com.puhui8.controller;

import com.puhui8.common.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author
 * @create 2018-12-20 上午10:30
 **/
@RestController
@Slf4j
public class WelcomeController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    @GetMapping(value = "/")
    public String Welcome(){
        //不加/ 是一个字符串
        return "/login";
    }

    @GetMapping(value = "/login.html")
    public String ll(){
        //不加/ 是一个字符串
        return "/ll";
    }
    /**
     * 针对该需求，Spring Security Web 提供了在http session中缓存请求的能力，也就是HttpSessionRequestCache。HttpSessionRequestCache所保存的请求必须封装成一个SavedRequest接口对象，实际上，HttpSessionRequestCache总是使用自己的SavedRequest缺省实现DefaultSavedRequest。
     ---------------------
     作者：九九八八
     来源：CSDN
     原文：https://blog.csdn.net/andy_zhang2007/article/details/84846764
     版权声明：本文为博主原创文章，转载请附上博文链接！
     */

    @GetMapping("index")
    public String index(Authentication authentication, Model model) {
        return "/index";
    }
}
