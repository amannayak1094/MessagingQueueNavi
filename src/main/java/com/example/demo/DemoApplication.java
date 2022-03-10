package com.example.demo;

import com.example.demo.consumer.Consumer;
import com.example.demo.model.IWorkHandler;
import com.example.demo.producer.impl.ProducerQueueImpl;
import com.example.demo.queue.impl.MessageQueueImpl;
import com.example.demo.queue.impl.QueueManagerImpl;
import com.example.demo.queue.spi.MessageQueue;
import com.example.demo.queue.spi.QueueManager;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws Exception {
//		System.out.println("args  "+args[0]);
		if(args.length<1) {
			throw new Exception("not enough arguments");
		}
		File file = new File(args[0]);
		Scanner sc = new Scanner(file);
		List<String> input=new ArrayList<>();
		while (sc.hasNextLine()){
			input.add(sc.nextLine());
		}

		// line 1 for consumer count
		int consumerCount=	Integer.parseInt(input.get(0));

		QueueManager qm=new QueueManagerImpl("manager1");
		qm.addQueue(new MessageQueueImpl("sample"));
		MessageQueue queue= qm.getQueueByName("sample");
		ProducerQueueImpl p=new ProducerQueueImpl(queue);

		for(int i=0;i<consumerCount;i++){
			queue.addConsumer(new Consumer("consumer-"+i));
		}
		List<String> msgs=new ArrayList<>();

		//all lines are msg to queue
		for(int i=1;i<input.size();i++){
			String msg=input.get(i);
			try{
				p.send(msg);
			}catch (Exception e){
				System.out.println("got error for msg:"+msg);
			}
		}
//		Thread.sleep(1000);
//		queue.removeConsumer(c2);
//		queue.addConsumer(c3);
//		for(String m:msgs){
//			try{
//				p.send(m);
//			}catch (Exception e){
//				System.out.println("got error for msg:"+m);
//			}
//		}

	}
}
