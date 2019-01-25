package com.puhui8.config;

import com.puhui8.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;


/**
 * 
 * @Title: RedisConfig
 * @Description: redis初始化配置
 * @Version:1.0.0
 * @author pancm
 * @date 2018年6月7日
 */
@Component
public class RedisConfig {

	/**
	 * 配置属性
	 */
	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.password}")
	private String password;

	@Value("${spring.redis.timeout}")
	private int timeout;

	@Value("${spring.redis.jedis.pool.max-idle}")
	private int maxIdle;

	@Value("${spring.redis.jedis.pool.max-wait}")
	private long maxWaitMillis;
	/**
	 * JedisPoolConfig 连接池
	 * 
	 * @return
	 */
	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		// 最大空闲数
		jedisPoolConfig.setMaxIdle(maxIdle);
		// 最大建立连接等待时间
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		// 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
		return jedisPoolConfig;
	}



	
	/**
	 * 配置工厂
	 */
	@Bean
	public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
		JedisConnectionFactory JedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
		/*
		  *    设置密码，如果为空，则不设置
		  *  可在 redis.conf 文件中设置: requirepass 密码
		 */
		if (StringUtils.isNotEmpty(password)) {
			JedisConnectionFactory.setPassword(password);
		}
		return JedisConnectionFactory;
	}
	@Bean
	@ConditionalOnMissingBean(StringRedisTemplate.class)//如果容器存在StringRedisTemplate就不注入了 否则注入
	public StringRedisTemplate stringRedisTemplate (JedisConnectionFactory jedisConnectionFactory) {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(jedisConnectionFactory);
		return template;
	}
	/**
	 * 注入封装RedisTemplate
	 */
	@Bean
	public RedisUtil redisUtil(StringRedisTemplate stringRedisTemplate) {
		RedisUtil redisUtil = new RedisUtil();
		redisUtil.setRedisTemplate(stringRedisTemplate);
		return redisUtil;
	}
}