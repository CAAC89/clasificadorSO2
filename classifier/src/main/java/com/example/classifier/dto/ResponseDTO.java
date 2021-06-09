package com.example.classifier.dto;

public class ResponseDTO {
	private String categoryNews;
	private int totalNews;
    private int classifiers;
    private int tokens;
    private float probability;
    
	public ResponseDTO() {
		super();
	}
	
	public ResponseDTO(String categoryNews, int totalNews, int classifiers, int tokens, float probability) {
		super();
		this.categoryNews = categoryNews;
		this.totalNews = totalNews;
		this.classifiers = classifiers;
		this.tokens = tokens;
		this.probability = probability;
	}

	public String getCategoryNews() {
		return categoryNews;
	}
	public void setCategoryNews(String categoryNews) {
		this.categoryNews = categoryNews;
	}
	public int getTotalNews() {
		return totalNews;
	}
	public void setTotalNews(int totalNews) {
		this.totalNews = totalNews;
	}
	public int getClassifiers() {
		return classifiers;
	}
	public void setClassifiers(int classifiers) {
		this.classifiers = classifiers;
	}
	public int getTokens() {
		return tokens;
	}
	public void setTokens(int tokens) {
		this.tokens = tokens;
	}
	public float getProbability() {
		return probability;
	}
	public void setProbability(float probability) {
		this.probability = probability;
	}



	@Override
	public String toString() {
		return "{categoryNews=" + categoryNews + ", totalNews=" + totalNews + ", classifiers=" + classifiers
				+ ", tokens=" + tokens + ", probability=" + probability + "}";
	}
	
	

    
}
