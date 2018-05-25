package com.ljw.crawlerdemo.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;


@EnableScheduling
@RabbitListener(queues = "hello")
@Component
public class MQTest {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Scheduled(fixedDelay = 3000)
    public void sendMessage(){
        amqpTemplate.convertAndSend("hello","郎建伟");
    }

    @Bean
    public Queue helloQueue(){
        return new Queue("hello");
    }

    @RabbitHandler
    public void receiveMessage(@Payload String hello){
        System.out.println("------"+new Date()+":"+hello);
    }



}
