package com.sankar.assignment.mongo.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.sankar.assignment.mongo.model.Article;

public interface ArticleRepository extends MongoRepository<Article, String> {
	
	public List<Article> findBySource(String source);
	
	public List<Article> findAllBy(TextCriteria textCriteria, Sort sort);
}
