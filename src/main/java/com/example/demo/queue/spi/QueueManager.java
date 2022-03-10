package com.example.demo.queue.spi;

import com.example.demo.queue.impl.MessageQueueImpl;

public interface QueueManager {
    void addQueue(MessageQueue queue) throws Exception ;
    void removeQueue(MessageQueue queue) throws Exception;
    MessageQueue getQueueByName(String name);
}
