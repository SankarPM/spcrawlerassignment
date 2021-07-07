package com.sankar.assignment.crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sankar.assignment.dto.NewsDTO;
import com.sankar.assignment.mongo.model.Article;
import com.sankar.assignment.mongo.repository.ArticleRepository;

@Component
public class CrawlerService {

	public final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ArticleRepository articleRepo;
	
	@Autowired
	JsoupRediffCrawlerService jsoupRediffCrawlerService;

	public List<NewsDTO> crawlRediffJsoup() {
		List<NewsDTO> newsList = new ArrayList<NewsDTO>();
		jsoupRediffCrawlerService.loadNewsDTO(newsList);
		List<Article> articleList = newsList.stream().map(e-> modelMapper.map(e, Article.class)).collect(Collectors.toList());
		articleRepo.saveAll(articleList);
		return newsList;
	}

}
