//package com.example.evrabbitmq.config;
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Component
//public class Sender {
//
//    @Autowired
//    private AmqpTemplate template;
//
//    @Scheduled(cron="0/5 * * * * ? ")   //每5秒执行一次
//    public void send() {
//    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    template.convertAndSend("opr_log",sdf.format(new Date())+"消息来啦！");
//    }
//}