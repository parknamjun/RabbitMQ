package com.example.rabbitmq;

import com.example.queuepublisher.dto.Person;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TopicPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("3.36.211.68");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        Person person = new Person(1L, "홍길동 tv.mobile.ac");
        //channel.basicPublish("Topic-Exchange", "tv.mobile.ac", null, person.toString().getBytes());

        person = new Person(2L, "홍길동 *.tv.*");
        channel.basicPublish("Topic-Exchange", "*.tv.*", null, person.toString().getBytes());

        person = new Person(2L, "홍길동 *.mobile.*");
        channel.basicPublish("Topic-Exchange", "*.mobile.*", null, person.toString().getBytes());

        person = new Person(2L, "홍길동 *.ac");
        channel.basicPublish("Topic-Exchange", "abc.ac", null, person.toString().getBytes());

        channel.close();
        connection.close();
    }
}