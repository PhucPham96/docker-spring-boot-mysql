//package com.example.dockerspringbootmysql.controller;
//
//import com.example.dockerspringbootmysql.service.RabbitMQSender;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(value = "/rabbitmq")
//@RequiredArgsConstructor
//public class RabbitMQDemoController {
//
//    final RabbitMQSender rabbitMQSender;
//
//    @PostMapping(value = "/sender")
//    public String producer() {
//        rabbitMQSender.send();
//        return "Message sent to the RabbitMQ Queue Successfully";
//    }
//
//}
