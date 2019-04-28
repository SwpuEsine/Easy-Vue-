package com.ev.evrabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author
 * @create 2019-01-29 上午11:53
 **/
@Configuration
public class RabbitMqConfig {
    @Value("${rmqconfig.log-queue.name}")
    public String LOG_QUEUE_NAME;

    @Bean
    public Queue logQueue(){
        //true代表持久化
        return new Queue(LOG_QUEUE_NAME,true);
    }
}
