package com.sankar.assignment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sankar.assignment.dto.NewsDTO;
import com.sankar.assignment.exception.DataNotFoundException;
import com.sankar.assignment.exception.TechException;
import com.sankar.assignment.mongo.model.Article;
import com.sankar.assignment.mongo.repository.ArticleDAO;
import com.sankar.assignment.util.Constants;

@Component
public class SearchService {

	@Autowired
	ArticleDAO articleDAO;
	
	public List<NewsDTO> getArticlesMatchingKeywords(String keyword) throws DataNotFoundException, TechException{
		List<NewsDTO> newsDTOList = new ArrayList<NewsDTO> ();
		List<Article> articleList = articleDAO.getKeywordMatchedArticles(keyword);
		if (CollectionUtils.isEmpty(articleList)) {
			throw new DataNotFoundException(Constants.DATNOTFOUND, "Search Keyword not found");
		} else {
			newsDTOList = articleList.stream().map(e -> {
				return new NewsDTO(e);
			}).collect(Collectors.toList());
		}
		return newsDTOList;
	}
}
