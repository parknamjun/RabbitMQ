package com.example.queuepublisher.controller;

import com.example.queuepublisher.dto.Person;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class TestController {
    private RabbitTemplate rabbitTemplate;

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

    @GetMapping("/headers/{name}")
    public String testHeadersAPI(@PathVariable("name") String name) throws IOException {
        Person person = new Person(1L, name);
        Message message = MessageBuilder.withBody(person.toString().getBytes())
                .setHeader("item1", "mobile")
                .setHeader("item2", "television").build();

        rabbitTemplate.send("Headers-Exchange", "", message);
        return "Success";
    }
}


