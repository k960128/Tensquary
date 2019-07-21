package com.tensquare.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tenquare.rabbit.RabbitApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitApplication.class)
public class ProductTest {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Test
	public void sendMsg1(){
		 rabbitTemplate.convertAndSend("tenquary","直接模式测试");
	}
	
	/**
	 * 分裂模式
	 */
	@Test
	public void sendMsg2(){
		 rabbitTemplate.convertAndSend("tensquare", "", "分裂模式");
		 
	}
	
	/**
	 * 主题模式
	 */
	@Test
	public void sendMsg3(){
		 rabbitTemplate.convertAndSend("topic20", "good.abc", "主题模式");
		 
	}
	
}
