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

    @RabbitListener(queues = "Mobile")
    public void receiveMessage(@Payload String payload) {
        System.out.println("Received message Mobile Headers Exchange: " + payload);
    }

    @RabbitListener(queues = "AC")
    public void receiveMessageAC(@Payload String payload) {
        System.out.println("Received message AC: " + payload);
    }

    @RabbitListener(queues = "TV")
    public void receiveMessageTV(@Payload String payload) {
        System.out.println("Received message TV: " + payload);
    }
}
