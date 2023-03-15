package com.example.queuepublisher.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Person implements Serializable {
    private Long id;
    private String name;
    private String timeStamp;

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
        this.timeStamp = new Date().toString();
    }

}
