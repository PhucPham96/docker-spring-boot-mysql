//package com.example.dockerspringbootmysql.controller;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RabbitMQReceiver {
//
//    private static Logger logger = LogManager.getLogger(RabbitMQReceiver.class.toString());
//
////    @RabbitHandler
//    @RabbitListener(queues = "rabbitmq.queue")
//    public void receiverQueue1(String object) {
//        logger.info("MenuOrder listener invoked - Consuming Message with MenuOrder Identifier : " + object);
//    }
//
////    @RabbitHandler
////    @RabbitListener(queues = "phuc1")
////    public void receiverQueue2(String object) {
////        logger.info("MenuOrder listener invoked - Consuming Message with MenuOrder Identifier : " + object);
////    }
//
////    @RabbitListener(queues = "phuc2")
////    public void receiverQueue3(String object) {
////        logger.info("MenuOrder listener invoked - Consuming Message with MenuOrder Identifier : " + object);
////    }
//}
