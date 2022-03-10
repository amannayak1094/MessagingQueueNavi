package com.example.demo.queue.impl;

import com.example.demo.model.IWorkHandler;
import com.example.demo.model.WorkResponse;
import com.example.demo.queue.spi.MessageQueue;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import java.util.*;

public class MessageQueueImpl implements MessageQueue {
    private Queue<String> q;
    private String name;
    private List<IWorkHandler> list;
    private ObjectMapper mapper;
    private Thread thread;
    public String getName() {
        return name;
    }
    public MessageQueueImpl(String name){
        mapper = new ObjectMapper();
        this.name=name;
        q=new LinkedList<>();
        list=new ArrayList<>();
        thread=new Thread(()->{
            try {
                onMessage();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
    @Override
    public void insertInQueue(String msg) {
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map<String, Object> x=parser.parseMap(msg);
        q.add(msg);
    }
    @Override
    public void addConsumer(IWorkHandler obj){
        list.add(obj);
    }

    @Override
    public void removeConsumer(IWorkHandler obj){
        list.remove(obj);
    }

    @Override
    public void onMessage() throws InterruptedException {
        while (true) {
            if (q.isEmpty() || list.size() == 0) {
                Thread.sleep(100);
                continue;
            }
            String msg = q.remove();
            for (IWorkHandler consumer : list) {
                WorkResponse status = consumer.doWork(msg);
                if (status == WorkResponse.FAILURE) {
                    System.out.println("Status of msg for consumer:" + consumer.getName() + " for msg" + msg);
                    status = consumer.doWork(msg);
                }
                System.out.println("Status of msg for consumer:" + consumer.getName() + " with status:" + status + " for msg:" + msg);
            }
        }
    }

    @Override
    public void destroy() {
        thread.stop();
    }
}
