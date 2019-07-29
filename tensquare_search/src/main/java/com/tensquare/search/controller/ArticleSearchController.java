package com.tensquare.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleSearchService;

import entity.Result;
import entity.StatusCode;

@Controller
@RequestMapping(value="/article")
@CrossOrigin
public class ArticleSearchController {

	@Autowired
	private ArticleSearchService articleSearchService;
	
	@RequestMapping(method = RequestMethod.POST)
	public Result save(@RequestBody Article article){
		//articleSearchService.save(article);
		return new Result(true, StatusCode.OK,"添加成功");
	}
}
