package com.puhui8.controller;

import com.puhui8.common.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author
 * @create 2018-11-28 下午5:34
 **/
@RestController
@Slf4j
public class SecurityController {

    //在认证之前  security会把这次请求 缓存到requestCache里面
    private RequestCache requestCache=new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();


    @Value("${security.login.page}")
    private String loginUrl;


    /**
     * 方法认证   需要身份认证的时候 跳转到这里
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "/authentication/require")
    //@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public JsonResult  requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String url=savedRequest.getRedirectUrl();
            log.info("引发跳转的url"+url);
            if (StringUtils.endsWithIgnoreCase(url,".html")){
                redirectStrategy.sendRedirect(request,response,loginUrl);
            }
        }
        return JsonResult.error("请登录后操作");
    }
}
