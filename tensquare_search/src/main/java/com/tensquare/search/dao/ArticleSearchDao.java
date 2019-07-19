package com.tensquare.search.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleSearchDao extends ElasticsearchRepository<String, String>{

}
