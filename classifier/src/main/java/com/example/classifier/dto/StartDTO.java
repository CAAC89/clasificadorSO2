package com.example.classifier.dto;

public class StartDTO {
	private String title;
	private String news_url;
	private String dicitionary;
	public StartDTO() {
		super();
	}
	public StartDTO(String title, String news_url, String dicitionary) {
		super();
		this.title = title;
		this.news_url = news_url;
		this.dicitionary = dicitionary;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNews_url() {
		return news_url;
	}
	public void setNews_url(String news_url) {
		this.news_url = news_url;
	}
	public String getDicitionary() {
		return dicitionary;
	}
	public void setDicitionary(String dicitionary) {
		this.dicitionary = dicitionary;
	}
	
	
	
}
