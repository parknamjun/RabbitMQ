package com.example.rabbitmq;

import com.example.queuepublisher.dto.Person;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class HeadersPublisher {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("3.36.211.68");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        Person person = null;
        Map<String, Object> headersMap = null;

        //match any
        person = new Person(1L, "홍길동 to Mobile-any, TV-any, item1:mobile, item2:television");
        headersMap = new HashMap<String, Object>();
        headersMap.put("item1", "mobile");
        headersMap.put("item2", "television");
        publish(channel, person, headersMap);

        //match any, all
        person = new Person(2L, "홍길동 to AC-all, Mobile-any,  item1:mobile, item2:ac");
        headersMap = new HashMap<String, Object>();
        headersMap.put("item1", "mobile");
        headersMap.put("item2", "ac");
        publish(channel, person, headersMap);

        channel.close();
        connection.close();
    }

    private static void publish(Channel channel, Person person, Map<String, Object> headersMap) throws IOException {
        BasicProperties basicProperties = new BasicProperties();
        basicProperties = basicProperties.builder().headers(headersMap).build();
        System.out.println(basicProperties.toString());
        System.out.println(person.toString());
        channel.basicPublish("Headers-Exchange", "", basicProperties, person.toString().getBytes());
    }
}