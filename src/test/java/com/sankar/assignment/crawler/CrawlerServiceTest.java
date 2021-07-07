package com.sankar.assignment.crawler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import com.sankar.assignment.dto.NewsDTO;
import com.sankar.assignment.mongo.model.Article;
import com.sankar.assignment.mongo.repository.ArticleRepository;

@RunWith(SpringRunner.class)
public class CrawlerServiceTest {

	@Mock
	ModelMapper modelMapper;

	@Mock
	ArticleRepository articleRepo;

	@InjectMocks
	CrawlerService crawlerService;

	@Mock
	JsoupRediffCrawlerService jsoupRediffCrawlerService;

	@Test
	public void crawlRediffJsoupTest() {
		when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(getArticle());
		doAnswer(invocation -> {
			List<NewsDTO> arg0 = invocation.getArgument(0);
			arg0.addAll(getNewsList());
			return null;
		}).when(jsoupRediffCrawlerService).loadNewsDTO(Mockito.anyList());

		List<NewsDTO> newsDTO = crawlerService.crawlRediffJsoup();
		assertEquals(1, newsDTO.size());
	}

	private Article getArticle() {
		Article a = new Article();
		a.setAuthor("Sankar");
		a.setDescription("Description");
		a.setHeadline("Headline");
		a.setSource("Source");
		return a;
	}

	private List<NewsDTO> getNewsList() {
		List<NewsDTO> newsList = new ArrayList<NewsDTO>();
		NewsDTO a = new NewsDTO();
		a.setAuthor("Sankar");
		a.setDescription("Description");
		a.setHeadline("Headline");
		a.setSource("Source");
		newsList.add(a);
		return newsList;
	}
}
