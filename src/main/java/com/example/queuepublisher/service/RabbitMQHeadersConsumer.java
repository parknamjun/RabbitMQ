package com.example.queuepublisher.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQHeadersConsumer {

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
