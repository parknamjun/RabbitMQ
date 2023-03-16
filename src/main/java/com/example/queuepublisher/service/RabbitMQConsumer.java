package com.example.queuepublisher.service;

import com.example.queuepublisher.dto.Person;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

@Service
public class RabbitMQConsumer {
/*
    @RabbitListener(queues = "Mobile")
    public void getMessageFromMobile(Person person) {
        System.out.println("RabbitMQConsumer - getMessageFromMobile=" + person.getName());
    }

    @RabbitListener(queues = "AC")
    public void getMessageFromAC(Person person) {
        System.out.println("RabbitMQConsumer - getMessageFromAC=" + person.getName());
    }

    @RabbitListener(queues = "TV")
    public void getMessageFromTV(Person person) {
        System.out.println("RabbitMQConsumer - getMessageFromTV=" + person.getName());
    }
*/
}
