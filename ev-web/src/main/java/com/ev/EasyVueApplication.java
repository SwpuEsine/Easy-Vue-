package com.ev;

import com.ev.listener.SpringContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan("com.ev") //这里分模块的情况下必须要 不然扫描不到
@MapperScan("com.ev.dao")
public class EasyVueApplication {
	public static void main(String[] args) {
		ApplicationContext app =SpringApplication.run(EasyVueApplication.class, args);
		SpringContextUtil.setApplicationContext(app);
	}
}
