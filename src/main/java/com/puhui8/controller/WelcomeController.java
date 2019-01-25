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
        return "login";
    }
    /**
     * 针对该需求，Spring Security Web 提供了在http session中缓存请求的能力，也就是HttpSessionRequestCache。HttpSessionRequestCache所保存的请求必须封装成一个SavedRequest接口对象，实际上，HttpSessionRequestCache总是使用自己的SavedRequest缺省实现DefaultSavedRequest。
     ---------------------
     作者：九九八八
     来源：CSDN
     原文：https://blog.csdn.net/andy_zhang2007/article/details/84846764
     版权声明：本文为博主原创文章，转载请附上博文链接！
     */

    /**
     * json请求
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/ll")
    public JsonResult login(HttpServletRequest request, HttpServletResponse response) {
        //如果是上一次的请求过来的 他会把上次的请求缓存起来  然后包装 然后日志记录
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            //缓存的请求   如果有app 可以根据是app发来的请求
            // 还是网页发来的进行区别处理 看看到底是重定向还是返回一个json数据
            log.info("引发跳转的请求是：{}", redirectUrl);
        }
        return JsonResult.ok("请登录");
    }


    @GetMapping("index")
    public String index(Authentication authentication, Model model) {
        model.addAttribute("user",authentication.getPrincipal());
        return "index";
    }
}
