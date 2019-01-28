package com.ev.exception;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ev.common.JsonResult;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ErrorProperties.IncludeStacktrace;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义返回404
 */
@RequestMapping("${server.error.path:${error.path:/error}}")
public class MyBasicExceptionController extends AbstractErrorController {

    private  ErrorProperties errorProperties;

    public MyBasicExceptionController(){
        super(null);
    }

    public MyBasicExceptionController(ErrorAttributes errorAttributes,
                                      ErrorProperties errorProperties) {
        this(errorAttributes, errorProperties, Collections.emptyList());
    }

    public MyBasicExceptionController(ErrorAttributes errorAttributes,
                                      ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorViewResolvers);
        Assert.notNull(errorProperties, "ErrorProperties must not be null");
        this.errorProperties = errorProperties;
    }

    @Override
    public String getErrorPath() {
        return this.errorProperties.getPath();
    }

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request,
                                  HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(
                request, isIncludeStackTrace(request, MediaType.TEXT_HTML)));
        response.setStatus(status.value());
        ModelAndView modelAndView = resolveErrorView(request, response, status, model);
        return (modelAndView != null) ? modelAndView : new ModelAndView("error", model);
    }

    @RequestMapping
    @ResponseBody
    public JsonResult error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.ALL));
        int status= (int) body.get("status");
        getException(request);
        System.out.println("错误码是"+status);
        String errorMessage="";
        if (status==404){
            errorMessage= "页面不存在";
        }else if (status==403){
            errorMessage= "权限不足";
        }
        return JsonResult.ok(status,errorMessage) ;
    }


    String  getException(HttpServletRequest request){
        Exception ex= (Exception) request.getAttribute("javax.servlet.error.exception");
        return  ex==null?"":ex.getMessage();
    }

    protected boolean isIncludeStackTrace(HttpServletRequest request,
                                          MediaType produces) {
        IncludeStacktrace include = getErrorProperties().getIncludeStacktrace();
        if (include == IncludeStacktrace.ALWAYS) {
            return true;
        }
        if (include == IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }

    /**
     * Provide access to the error properties.
     * @return the error properties
     */
    protected ErrorProperties getErrorProperties() {
        return this.errorProperties;
    }

}
