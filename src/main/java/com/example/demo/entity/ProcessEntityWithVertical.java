package com.example.demo.entity;

public interface ProcessEntityWithVertical<T extends ProcessStep> extends ProcessEntity<T> {

    String getVerticalCode();

    void setVerticalCode(String code);

}
