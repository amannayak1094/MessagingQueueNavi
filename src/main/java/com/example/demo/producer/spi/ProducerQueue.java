package com.example.demo.producer.spi;

public interface ProducerQueue {
    void send(String msg) throws Exception;
}
