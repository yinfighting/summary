package com.learn.knowledge.entity;

import lombok.Data;

/**
 * @description:
 * @author: YHR
 * @date: Created in 2020/3/25 15:24
 * @version:
 * @modified By:
 */
@Data
public class People {

    private String name;
    private Integer age;
    private Double height;

    public People() {
    }

    public People(String name, Integer age, Double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }
}
