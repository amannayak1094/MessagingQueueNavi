/**
 * Copyright (c) Walmart Stores, Inc.  All rights reserved.
 */

package com.example.demo;

import com.example.demo.queue.impl.MessageQueueImpl;
import com.example.demo.queue.impl.QueueManagerImpl;
import com.example.demo.queue.spi.MessageQueue;
import com.example.demo.queue.spi.QueueManager;
import org.junit.jupiter.api.Test;
//import org.springframework.util.Assert;
import org.junit.jupiter.api.Assertions;

public class QueueManagerTest {
    @Test
    public void testQueueManger() throws Exception {
        QueueManager qm=new QueueManagerImpl("test-manager");
        MessageQueue mq1=new MessageQueueImpl("sample-queue1");
        MessageQueue mq2=new MessageQueueImpl("sample-queue2");
        MessageQueue mq3=new MessageQueueImpl("sample-queue3");
        qm.addQueue(mq1);
        qm.addQueue(mq2);
        qm.addQueue(mq3);
        Assertions.assertNotNull(qm.getQueueByName("sample-queue1"), "Queue shouldn't be null");
        Assertions.assertNotNull(qm.getQueueByName("sample-queue2"), "Queue shouldn't be null");
        Assertions.assertNotNull(qm.getQueueByName("sample-queue3"), "Queue shouldn't be null");
    }

    @Test
    public void testQueueMangerError1() throws Exception {
        QueueManager qm=new QueueManagerImpl("test-manager");
        MessageQueue mq1=new MessageQueueImpl("sample-queue1");
        qm.addQueue(mq1);
        Assertions.assertNotNull(qm.getQueueByName("sample-queue1"), "Queue shouldn't be null");
        Assertions.assertNull(qm.getQueueByName("sample-queue2"), "Queue shouldn't be null");
        try{
            qm.addQueue(mq1);
            Assertions.assertTrue(false);
        }catch (Exception e){
            Assertions.assertEquals("queue name is already taken", e.getMessage());
        }
    }
}
