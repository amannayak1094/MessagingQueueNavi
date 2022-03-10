package com.example.demo.producer.impl;

import com.example.demo.producer.spi.ProducerQueue;
import com.example.demo.queue.spi.MessageQueue;

public class ProducerQueueImpl implements ProducerQueue {
    MessageQueue queue;

    public ProducerQueueImpl(MessageQueue queue){
        this.queue=queue;
    }
    public void send(String msg) throws Exception {
        queue.insertInQueue(msg);
    }
}
