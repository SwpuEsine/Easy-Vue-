package com.puhui8;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.puhui8.dao")
public class SpringsecurityEnterprisedemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityEnterprisedemoApplication.class, args);
	}
}
