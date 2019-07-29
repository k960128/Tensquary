package com.tensquare.search.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.tensquare.search.pojo.Article;


public interface ArticleSearchDao extends ElasticsearchRepository<Article, String>{
	
	
}
