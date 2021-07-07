package com.sankar.assignment.crawler;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sankar.assignment.dto.NewsDTO;
import com.sankar.assignment.util.TextCleaner;


@Component
public class JsoupRediffCrawlerService {

	public final Logger log = LoggerFactory.getLogger(this.getClass());

	private static final int MAX_DEPTH = 2;

	HashSet<String> links = new HashSet<String>();

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy", Locale.ENGLISH);

	void loadNews(String URL, int depth, List<NewsDTO> newsList) {
		if ((!links.contains(URL) && (depth < MAX_DEPTH))) {
//           log.info(">> Depth: " + depth + " [" + URL + "]");
			try {
				Document document = Jsoup.connect(URL).timeout(5 * 1000).get();
				if (URL.contains("/news") && URL.endsWith(".htm")) {
//                	log.info("URL {} ",URL);
					if (!links.contains(URL)) {
						links.add(URL);
						NewsDTO news = getNewsDTOFromDocument(URL, document);
						newsList.add(news);
					}
				}
				Elements linksOnPage = document.select("a[href]");
				depth++;
				for (Element page : linksOnPage) {
					loadNews(page.attr("abs:href"), depth, newsList);
				}
			} catch (IOException e) {
				log.error("For '" + URL + "': " + e.getMessage());
			} catch (Exception e) {
				log.error("Exception {}", e);
			}
		}
	}

	NewsDTO getNewsDTOFromDocument(String URL, Document document) {
		Element article = document.getElementById("arti_content_n");
		String title = document.title();
		Elements metaTags = document.getElementsByTag("meta");
		String author = metaTags.stream().filter(e -> e.attr("name").equals("author")).map(e -> e.attr("content"))
				.findFirst().orElse("");
		String datePublished = metaTags.stream().filter(e -> e.attr("itemprop").equals("datePublished"))
				.map(e -> e.attr("content")).findFirst().orElse(LocalDate.now().toString());
		log.info("Author :: {} , title :: {} publishDate :: {} ", author, title, datePublished);
		log.info("URL {} ", URL);
		NewsDTO news = new NewsDTO();
		news.setAuthor(author);
		news.setDescription(TextCleaner.getCleanTextForRediff(article.text()));
		news.setHeadline(title);
		news.setPublishDate(LocalDate.parse(datePublished, formatter));
		news.setSource("Rediff");
		news.setUrl(URL);
		return news;
	}
	
	public void loadNewsDTO(List<NewsDTO> newsList) {
		loadNews("https://www.rediff.com/news", 0, newsList);
	}
}
