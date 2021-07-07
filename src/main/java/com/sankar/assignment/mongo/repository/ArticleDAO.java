package com.sankar.assignment.mongo.repository;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Component;

import com.sankar.assignment.exception.DataNotFoundException;
import com.sankar.assignment.exception.TechException;
import com.sankar.assignment.mongo.model.Article;
import com.sankar.assignment.util.Constants;

@Component
public class ArticleDAO {

	@Autowired
	ArticleRepository articleRepository;
	
	public final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public List<Article> getKeywordMatchedArticles(String keyword) throws TechException, DataNotFoundException {
		List<Article> articleList = null;
		try {
			TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matchingAny(keyword);
			Sort sort = Sort.by("score");
			articleList = articleRepository.findAllBy(textCriteria, sort);
		} catch (Exception e) {
			log.error("Exception ", e);
			throw new TechException(Constants.DBTECHEXCEPTIONCODE, e.getMessage());
		} 
		if (CollectionUtils.isEmpty(articleList)) {
			throw new DataNotFoundException(Constants.DATNOTFOUND, "Search Keyword not found");
		}
 		return articleList;
	}
}
