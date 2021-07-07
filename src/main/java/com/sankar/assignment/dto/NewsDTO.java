package com.sankar.assignment.dto;

import java.time.LocalDate;

import com.sankar.assignment.mongo.model.Article;

public class NewsDTO {

	public String headline;
	public LocalDate publishDate;
	public String author;
	public String description;
	public String source;
	public String url;
	
	public NewsDTO(Article article) {
		this.url = article.getUrl();
		this.headline = article.getHeadline();
		this.publishDate = article.getPublishDate();
		this.source = article.getSource();
		this.author = article.getAuthor();
		this.description = article.getDescription();
	}
	
	public NewsDTO() {
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
