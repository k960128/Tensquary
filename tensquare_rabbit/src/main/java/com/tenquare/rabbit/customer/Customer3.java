package com.tenquare.rabbit.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello-queue")
public class Customer3 {
	
	@RabbitHandler
	public void getMsg(String msg){
		System.out.println("hello-queue:"+msg);
	}

}
