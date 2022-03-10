package com.example.demo.queue.spi;

import com.example.demo.model.IWorkHandler;
import com.example.demo.model.WorkResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

public interface MessageQueue {
    String getName();
    void insertInQueue(String msg);

    void addConsumer(IWorkHandler obj);

    void removeConsumer(IWorkHandler obj);

    void onMessage() throws InterruptedException;

    void destroy();
}
