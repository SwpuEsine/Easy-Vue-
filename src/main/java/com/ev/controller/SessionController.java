package com.ev.controller;

import com.ev.common.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @create 2019-01-24 上午11:57
 **/
@RestController
@RequestMapping("/session")
public class SessionController {
    @GetMapping(value = "/timeout")
    public JsonResult SessionTime(){
        return JsonResult.error("登录超时,请重新登录");
    }
}
