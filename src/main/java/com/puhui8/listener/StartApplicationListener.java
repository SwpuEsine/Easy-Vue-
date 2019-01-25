package com.puhui8.listener;
 
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class StartApplicationListener implements ApplicationListener<ContextRefreshedEvent>{

	//会在所有的bean都记载后执行  也非常不错   方便注入
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//在spring中InitializingBean接口也提供了类似的功能，只不过它进行操作的时机是在所有bean都被实例化之后才进行调用。根据不同的业务场景和需求，可选择不同的方案来实现。
		log.info("加载系统配置...");
		log.info("系统配置加载完成...");
	}
 
}
