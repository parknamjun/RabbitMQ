package com.example.queuepublisher.service;

import com.example.queuepublisher.dto.Person;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    @RabbitListener(queues = "Mobile")
    public void getMessageFromMobile(Person person) {
        System.out.println("getMessageFromMobile=" + person.getName());
    }

    @RabbitListener(queues = "AC")
    public void getMessageFromAC(Person person) {
        System.out.println("getMessageFromAC=" + person.getName());
    }
}
