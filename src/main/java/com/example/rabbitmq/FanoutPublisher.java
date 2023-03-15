package com.example.rabbitmq;

import com.example.queuepublisher.dto.Person;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class FanoutPublisher {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("3.36.211.68");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        for(int i = 0; i< 3; i++) {
            Person person = new Person(1L, "홍길동-Mobile");
            channel.basicPublish("Fanout-Exchange", "", null, person.toString().getBytes());
            Thread.sleep(300);
        }

        for(int i = 0; i< 3; i++) {
            Person person = new Person(1L, "홍길동-AC");
            channel.basicPublish("Fanout-Exchange", "", null, person.toString().getBytes());
            Thread.sleep(300);
        }

        channel.close();
        connection.close();
    }
}
