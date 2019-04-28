package com.ev.listener;
 
import com.ev.common.SecurityPrefix;
import com.ev.core.PackageScanner;
import com.ev.entity.SysLogSwtich;
import com.ev.service.RedisService;
import com.ev.service.impl.SysLogSwtichService;
import com.ev.service.impl.SysResourceServiceImpl;
import com.ev.service.impl.SysRoleServiceImpl;
import com.ev.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
public class StartApplicationListener implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	SysResourceServiceImpl sysResourceService;
	//会在所有的bean都记载后执行  也非常不错   方便注入
	@Autowired
	SysRoleServiceImpl sysRoleService;
	@Autowired
	RedisService redisService;
	@Autowired
	SysLogSwtichService sysLogSwtichService;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//上下文启动  加载数据库信息到redis
		//在spring中InitializingBean接口也提供了类似的功能，只不过它进行操作的时机是在所有bean都被实例化之后才进行调用。根据不同的业务场景和需求，可选择不同的方案来实现。
		log.info("加载系统配置...");
		boolean flush=redisService.flushAll();
		if (flush){
			log.info("清空REDIS完成........");
		}
		List<String> roleName = sysRoleService.findRoleName();
		for (String s : roleName) {
			List<String> rolePaths = sysResourceService.findPathByResourceName(s);
			log.info(rolePaths.toString());
			if (rolePaths!=null && !rolePaths.isEmpty()){
				redisService.setRoleToPaths(s,rolePaths);
			}
		}

		/**
		 * 开始扫描包注解
		 */
		sysLogSwtichService.delete();
		List<SysLogSwtich> sysLogSwtiches = PackageScanner.doScan("com.ev.controller");
		sysLogSwtichService.insertList(sysLogSwtiches);
		log.info("系统配置加载完成...");
	}
 
}
