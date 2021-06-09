package com.example.classifier.dto;

public class CategoryNewsDTO {

    private String categoryNews;
    private int classifiers;
    private int tokens;

    
    
    public CategoryNewsDTO(String categoryNews, int classifiers, int tokens) {
		super();
		this.categoryNews = categoryNews;
		this.classifiers = classifiers;
		this.tokens = tokens;
	}

	public String getCategoryNews() {
        return categoryNews;
    }

    public void setCategoryNews(String categoryNews) {
        this.categoryNews = categoryNews;
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

	@Override
	public String toString() {
		return "{categoryNews=" + categoryNews + ", classifiers=" + classifiers + ", tokens=" + tokens+ "}";
	}
    
    
}
