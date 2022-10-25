package com.example.dockerspringbootmysql.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQSender {

    final AmqpTemplate rabbitTemplate;
    final Queue queue;
    private static Logger logger = LogManager.getLogger(RabbitMQSender.class.toString());

    public void send() {
        String object = "phuc test";
        rabbitTemplate.convertAndSend(queue.getName(), object);
//        rabbitTemplate.convertAndSend("phuc1", "phuc1");
        logger.info("Sending Message to the Queue : " + object);
    }
}
