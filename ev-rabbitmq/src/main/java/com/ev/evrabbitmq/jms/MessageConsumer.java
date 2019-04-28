package com.ev.evrabbitmq.jms;

import com.ev.evelasticsearch.entity.SysLog;
import com.ev.evelasticsearch.service.SysLogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author
 * @create 2019-01-29 下午12:16
 **/
@Component
@Slf4j
public class MessageConsumer {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private SysLogService sysLogService;



    @RabbitListener(queues = "${rmqconfig.log-queue.name}")
    public void onMessage(String jsonLog){
        System.out.println("开始接收消息"+jsonLog);
        SysLog sysLog=null;
        try {
            sysLog= objectMapper.readValue(jsonLog, SysLog.class);
            sysLogService.save(sysLog);
            log.info("索引构建完成");
        } catch (IOException e) {
            log.error(e.getMessage());
            //e.printStackTrace();
        }
    }
}
