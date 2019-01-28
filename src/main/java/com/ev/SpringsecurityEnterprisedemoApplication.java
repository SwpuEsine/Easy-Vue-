package com.ev;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.ev.dao")
public class SpringsecurityEnterprisedemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityEnterprisedemoApplication.class, args);
	}
}
