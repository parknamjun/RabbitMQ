package com.example.queuepublisher.controller;

import com.example.queuepublisher.dto.Person;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BasicProperties;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class TestController {
    RabbitTemplate rabbitTemplate;

    @Autowired
    public TestController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/test/{name}")
    public String testAPI(@PathVariable("name") String name) {
        Person p = new Person(1L, name);
        rabbitTemplate.convertAndSend("Mobile", p);
        rabbitTemplate.convertAndSend("Direct-Exchange", "mobile", p);
        rabbitTemplate.convertAndSend("Fanout-Exchange", "", p);
        rabbitTemplate.convertAndSend("Topic-Exchange", "tv.mobile.ac", p);
        return "Success";
    }

    @GetMapping("/testheaders/{name}")
    public String testHeadersAPI(@PathVariable("name") String name) throws IOException {
        Person person = new Person(1L, name);
        Map<String, Object> headersMap = new HashMap<String, Object>();
        headersMap.put("item1", "mobile");
        headersMap.put("item2", "television");

        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties();
        basicProperties = basicProperties.builder().headers(headersMap).build();


        Message message = MessageBuilder.withBody(person.toString())
                        .setHeader("item1", "mobile")
                        .setHeader("item2", "television")
                        .build();

        rabbitTemplate.send("Headers-Exchange", "", message);
        return "Success";
    }
}


//match any
        person = new Person(1L, "홍길동 to Mobile-any, TV-any, item1:mobile, item2:television");


        publish(channel, person, headersMap);

