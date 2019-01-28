package com.ev.aspect;

import com.ev.annoation.Action;
import com.ev.entity.SysLog;
import com.ev.entity.SysUser;
import com.ev.utils.WebUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
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
    public LogAspect(){
        System.out.println("初始化日志切面");
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
            //是否发生异常
            Boolean isException=false;
            //是否处理
            Boolean isProcess=true;

            SysLog sysLog=new SysLog();
            sysLog.setException(isException);
            sysLog.setProcess(isProcess);
            sysLog.setClassName(className);
            sysLog.setCreateTime(new Date());
            sysLog.setMethodName(methodName);
            SysUser sysUser= (SysUser) session.getAttribute("user");
            sysLog.setUserName("admin");//这里也从redis 或者session中去读取  spring session去读
            sysLog.setUrl(httpServletRequest.getRequestURI());
            sysLog.setIpAddress(WebUtil.getIpAddr(httpServletRequest));

            Object proceed=null;
            if (classAnnotaion==null){
                proceed  = proceedingJoinPoint.proceed();
            }else {
                MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
                Method method = signature.getMethod();
                Action annotation = method.getAnnotation(Action.class);
                if (annotation!=null && annotation.isEnable()==true){
                    long start =System.currentTimeMillis();
                    try {
                        proceed  = proceedingJoinPoint.proceed();
                    }catch (Throwable e){
                        //log.error(e.getMessage());
                        isException=true;
                        isProcess=false;
                    }
                    long end=System.currentTimeMillis();
                    sysLog.setDuration(end-start);
                    sysLog.setParameters(getParameters(proceedingJoinPoint,method));
                }
            }
        return proceed;
    }


    private String  getParameters(ProceedingJoinPoint joinPoint,Method method) throws IOException {
        StringBuilder params = new StringBuilder();
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            params = handleParams(params, args, Arrays.asList(paramNames));
        }
        return params.toString();
    }

    private StringBuilder handleParams(StringBuilder params, Object[] args, List paramNames) throws IOException {
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Map) {
                Set set = ((Map) args[i]).keySet();
                List list = new ArrayList();
                List paramList = new ArrayList<>();
                for (Object key : set) {
                    list.add(((Map) args[i]).get(key));
                    paramList.add(key);
                }
                return handleParams(params, list.toArray(), paramList);

            } else {
                if (args[i] instanceof Serializable) {
                    Class<?> aClass = args[i].getClass();
                    try {
                        aClass.getDeclaredMethod("toString", new Class[]{null});
                        // 如果不抛出 NoSuchMethodException 异常则存在 toString 方法 ，安全的 writeValueAsString ，否则 走 Object的 toString方法
                        params.append("  ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i]));
                    } catch (NoSuchMethodException e) {
                        params.append("  ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i].toString()));
                    }

                } else if (args[i] instanceof MultipartFile) {
                    MultipartFile file = (MultipartFile) args[i];
                    params.append("  ").append(paramNames.get(i)).append(": ").append(file.getName());
                } else {
                    params.append("  ").append(paramNames.get(i)).append(": ").append(args[i]);
                }
            }
        }
        return params;
    }
}
