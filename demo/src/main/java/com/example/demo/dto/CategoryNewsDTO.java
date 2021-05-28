package com.example.demo.dto;

public class CategoryNewsDTO {

    private String categoryNews;
    private int classifiers;
    private int tokens;

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
}
