//package com.ev.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
//import org.springframework.security.web.savedrequest.RequestCache;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author
// * @create 2018-12-20 上午10:30
// **/
//@RestController
//@Slf4j
//public class WelcomeController {
//
//
//    @GetMapping(value = "/")
//    public String Welcome(){
//        //不加/ 是一个字符串
//        return "/login";
//    }
//
//    @GetMapping(value = "/login.html")
//    public String ll(){
//        //不加/ 是一个字符串
//        return "/ll";
//    }
//}
