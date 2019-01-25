package com.puhui8.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 日志拦截器
 *
 * @author
 * @create 2018-11-28 上午9:43
 **/
@Aspect
@Component
public class LogAspect {
    //切片日志拦截  aspect是类
    public LogAspect(){
        System.out.println("初始化日志切面");
    }

    //第一个* public  包或者子包  任何类的任何方法 任何参数
    @Around("execution(* com.puhui8.controller..*.*(..))")
    public Object doAudit(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("切片start");

        Object[] args = proceedingJoinPoint.getArgs();//方法参数
        for (Object arg : args) {
            System.out.println(arg);//获取方法参数 可能是一般变量 可能是类变量
        }

        //类名
        String simpleName = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
        System.out.println(simpleName);

        //包名 类名
        String declaringTypeName = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        System.out.println(declaringTypeName);

        //方法名
        String name = proceedingJoinPoint.getSignature().getName();
        System.out.println(name);


        Class<?> target = proceedingJoinPoint.getTarget().getClass();//获取目标对象


        long start =System.currentTimeMillis();
        Object proceed = proceedingJoinPoint.proceed();

        long end=System.currentTimeMillis();

        System.out.println("切片end");
        System.out.println("耗时"+(end-start));
        return proceed;
    }

}
