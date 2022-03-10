package com.example.demo.queue.impl;

import com.example.demo.queue.spi.MessageQueue;
import com.example.demo.queue.spi.QueueManager;

import java.util.HashMap;
import java.util.Map;

public class QueueManagerImpl implements QueueManager {
    private Map<String, MessageQueue> msgQueueMap;
    private String name;
    public QueueManagerImpl(String name){
        this.name=name;
        msgQueueMap=new HashMap<>();
    }

    public void addQueue(MessageQueue queue) throws Exception {
        if(msgQueueMap.containsKey(queue.getName().toLowerCase().trim())){
            throw new Exception("queue name is already taken");
        }
        msgQueueMap.put(queue.getName().toLowerCase().trim(), queue);
    }

    public void removeQueue(MessageQueue queue) throws Exception {
        if(msgQueueMap.containsKey(queue.getName().toLowerCase().trim())){
            msgQueueMap.remove(queue.getName().toLowerCase().trim());
        }
    }

    public MessageQueue getQueueByName(String name){
        return msgQueueMap.get(name.toLowerCase().trim());
    }
}
