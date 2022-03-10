package com.example.demo.model;

public interface IWorkHandler<T> {
    WorkResponse doWork(final T msg);
    String getName();
}
