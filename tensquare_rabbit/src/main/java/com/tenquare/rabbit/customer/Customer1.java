package com.tenquare.rabbit.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "tenquary")
public class Customer1 {
	
	@RabbitHandler
	public void getMsg(String msg){
		System.out.println("tenquary："+msg);
	}

}
