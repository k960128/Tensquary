package com.tensquare.user.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	public void sendSms(String mobile){
		//生成6位随机数
		String checkcode = RandomStringUtils.randomNumeric(6);
		//缓存放一次
		redisTemplate.opsForValue().set("check_"+checkcode,checkcode,6,TimeUnit.SECONDS);
		Map<String,String> map = new HashMap<String,String>();
		map.put("mobile", mobile);
		map.put("checkcode", checkcode);
		//给用户发一份
		rabbitTemplate.convertAndSend("sms",map);
		//测试
		System.out.println("验证码："+checkcode);
	}

}
