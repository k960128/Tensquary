package com.tensquare.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tensquare.user.service.UserService;

import entity.Result;
import entity.StatusCode;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/sendsms/{mobile}" ,method = RequestMethod.POST)
	public Result sendSms(@PathVariable String mobile){
		userService.sendSms(mobile);
		return new Result(true,StatusCode.OK,"发送成功");
	}

	@RequestMapping(method = RequestMethod.GET)
	public Result user(){
		System.out.println("进来了！！！！");
		return new Result(true,StatusCode.OK,"进来了");
	}
}
