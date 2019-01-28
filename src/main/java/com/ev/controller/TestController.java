package com.ev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @create 2019-01-25 下午2:49
 **/
@RestController
public class TestController {

    @GetMapping(value = "/test500")
    public String test500(){
        throw new RuntimeException("500");
        //return null;
    }
}
