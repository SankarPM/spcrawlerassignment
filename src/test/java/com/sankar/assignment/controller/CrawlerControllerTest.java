package com.sankar.assignment.controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.sankar.assignment.crawler.CrawlerService;

@WebMvcTest(CrawlerController.class)
public class CrawlerControllerTest {

	private MockMvc mockMvc;
	
	@Rule
    public MockitoRule rule = MockitoJUnit.rule();
	
	@Mock
	private CrawlerService crawlerService;

	@InjectMocks
	CrawlerController controller;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testController() throws Exception {
		System.out.println("Came here");
		this.mockMvc.perform(MockMvcRequestBuilders.get("/rediffnews")).andExpect(MockMvcResultMatchers.status().isOk());
	}

}
