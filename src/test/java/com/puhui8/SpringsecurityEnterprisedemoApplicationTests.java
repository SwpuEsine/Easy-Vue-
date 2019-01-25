package com.puhui8;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringsecurityEnterprisedemoApplicationTests {

	//伪造MVC环境

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}

}
