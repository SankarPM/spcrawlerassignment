package com.sankar.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sankar.assignment.crawler.CrawlerService;
import com.sankar.assignment.dto.NewsDTO;
import com.sankar.assignment.exception.DataNotFoundException;
import com.sankar.assignment.exception.TechException;
import com.sankar.assignment.service.SearchService;

@RestController
public class CrawlerController {

	@Autowired
	CrawlerService service;
	
	@Autowired
	SearchService searchService;
	
	@GetMapping("rediffnews")
	public List<NewsDTO> crawlRediffNews() {
		return service.crawlRediffJsoup();
	}
	
	@GetMapping("searchbykeyword")
	public List<NewsDTO> fetchArticlesByKeyword( @RequestParam(required = false) String keyword) throws DataNotFoundException, TechException{
		return searchService.getArticlesMatchingKeywords(keyword);
	}
}
