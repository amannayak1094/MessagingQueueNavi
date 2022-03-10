package com.example.demo.consumer;

import com.example.demo.model.IWorkHandler;
import com.example.demo.model.WorkResponse;

public class Consumer implements IWorkHandler<String> {

    private String name;
    public Consumer(String name){
        this.name=name;
    }

   @Override
    public WorkResponse doWork(String msg) {
        System.out.println("Consumer-"+name+": got message from queue:"+msg);
        return WorkResponse.SUCCESS;
    }

    @Override
    public String getName() {
        return name;
    }

}
