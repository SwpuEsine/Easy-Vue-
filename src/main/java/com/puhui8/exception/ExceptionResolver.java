package com.puhui8.exception;

import com.puhui8.common.JsonResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理  这个是用来处理应用的异常 也就是controller中的异常 在容器的异常是无法捕获的
 *
 * @author
 * @create 2018-11-27 下午5:40
 **/
@ControllerAdvice
public class ExceptionResolver {
    //异常处理非常经典
    //https://blog.csdn.net/hao_kkkkk/article/details/80538955

    /*@ExceptionHandler({CustomException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> resolverCustomerException(CustomException ex){
        //异常响应码  默认200
        Map<String,Object> result=new HashMap<>();
        result.put("id",ex.getId());
        result.put("message",ex.getMessage());
        return result;
    }*/

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public JsonResult resolverCustomerException(HttpServletRequest request, Exception ex){
        ex.printStackTrace();
        if (ex instanceof CustomException){
            return JsonResult.error(530,ex.getMessage());
        }
        return JsonResult.error(ex.getMessage());
    }
}
