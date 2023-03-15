package com.example.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("Consumer start");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("3.36.211.68");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("Message received=" + message);
        };

        System.out.println("Mobile que------------------------");
        channel.basicConsume("Mobile", true, deliverCallback, consumerTag -> {});
        System.out.println("TV que------------------------");
        channel.basicConsume("TV", true, deliverCallback, consumerTag -> {});
        System.out.println("AC que------------------------");
        channel.basicConsume("AC", true, deliverCallback, consumerTag -> {});
    }
}
