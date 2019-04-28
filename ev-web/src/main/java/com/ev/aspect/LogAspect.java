package com.ev.aspect;

import com.ev.annoation.Action;
import com.ev.entity.SysLog;
import com.ev.entity.SysUser;
import com.ev.utils.StringUtil;
import com.ev.utils.WebUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 日志拦截器
 *
 * @author
 * @create 2018-11-28 上午9:43
 **/
@Aspect
@Slf4j
@Component
public class LogAspect {
    @Autowired
    private ObjectMapper objectMapper;
    //切片日志拦截  aspect是类

    @Autowired
    private AmqpTemplate template;


    @Value("${rmqconfig.log-queue.name}")
    public String LOG_QUEUE_NAME;

    public LogAspect(){
        //System.out.println("初始化日志切面");
    }

    //第一个* public  包或者子包  任何类的任何方法 任何参数
    @Around("execution(* com.ev.controller..*.*(..))")
    public Object doAudit(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
            HttpServletRequest httpServletRequest = WebUtil.getHttpServletRequest();
            HttpSession session = httpServletRequest.getSession();

            String methodName = proceedingJoinPoint.getSignature().getName();
            Class target = proceedingJoinPoint.getTarget().getClass();
            String className=target.getName();
            Action classAnnotaion = (Action)target.getAnnotation(Action.class);
            Object proceed=null;
            if (classAnnotaion==null){
                proceed  = proceedingJoinPoint.proceed();
            }else {
                //是否发生异常
                Boolean isException=false;
                //是否处理
                Boolean isProcess=true;

                SysLog sysLog=new SysLog();
                sysLog.setIsException(isException);
                sysLog.setIsProcess(isProcess);
                sysLog.setClassName(className);
                sysLog.setCreateTime(new Date());
                sysLog.setMethodName(methodName);
                SysUser sysUser= (SysUser) session.getAttribute("user");
                String userName="";
                if (sysUser!=null){
                    userName=sysUser.getUserName();
                }else {
                    userName="未登录用户";
                }
                sysLog.setUserName(userName);//这里也从redis 或者session中去读取  spring session去读
                sysLog.setUrl(httpServletRequest.getRequestURI());
                sysLog.setIpAddress(WebUtil.getIpAddr(httpServletRequest));
                sysLog.setSysLogId(UUID.randomUUID().toString());

                MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
                Method method = signature.getMethod();
                Action annotation = method.getAnnotation(Action.class);
                if (annotation!=null && annotation.isEnable()==true){
                    long start =System.currentTimeMillis();
                    try {
                        proceed  = proceedingJoinPoint.proceed();
                    }catch (Exception e){
                        //log.error(e.getMessage());
                        isException=true;
                        isProcess=false;
                        e.printStackTrace();
                        //throw e;
                    }finally {
                        long end=System.currentTimeMillis();
                        sysLog.setDuration(end-start);
                        sysLog.setParameters(getParameters());
                        template.convertAndSend(LOG_QUEUE_NAME,objectMapper.writeValueAsString(sysLog));
                    }
                }else {
                    proceed  = proceedingJoinPoint.proceed();
                }
            }
        return proceed;
    }


    private String getParameters () throws JsonProcessingException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Map<String,String[]> logParams=request.getParameterMap();
        return StringUtil.mapToString(logParams);
    }
}
