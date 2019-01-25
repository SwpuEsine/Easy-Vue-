package com.puhui8.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author
 * @create 2018-11-28 上午10:28
 **/
@Slf4j
@Controller(value = "/async")
public class AsyncController {

    @RequestMapping("/order")
    public String order() throws InterruptedException {
        log.info("主线程开始....");
        Thread.sleep(1000);
        log.info("主线程结束");
        return "success";
    }

    @GetMapping(value = "/test")
    public ModelAndView  fuck(){
        return new ModelAndView("/sign.html");
    }
}
