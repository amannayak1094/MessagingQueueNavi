/**
 * Copyright (c) Walmart Stores, Inc.  All rights reserved.
 */

package com.example.demo;

import com.example.demo.consumer.Consumer;
import com.example.demo.queue.impl.MessageQueueImpl;
import com.example.demo.queue.impl.QueueManagerImpl;
import com.example.demo.queue.spi.MessageQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

public class MessageQueueImplTest {

    @Mock
    Consumer consumer;

    @BeforeEach
    public void setUp() throws Exception {
//        consumer=Mockito.mock(Consumer.class);
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testMessageQueue() throws Exception {
        Consumer consumer=Mockito.mock(Consumer.class);
        MessageQueue mq1=new MessageQueueImpl("sample-queue1");
        mq1.addConsumer(consumer );
        mq1.insertInQueue("{\"test\":\"sample\"}");
        mq1.insertInQueue("{\"test\":\"sample\"}");
        mq1.insertInQueue("{\"test\":\"sample\"}");
        Thread.sleep(1000);
        Mockito.verify(consumer, Mockito.times(3));
        try{
            mq1.insertInQueue("{msg}");
            Assertions.assertTrue(false);
        }catch (Exception e){
            Assertions.assertTrue(true, "expected to get error");
        }
          mq1.destroy();
    }


}
