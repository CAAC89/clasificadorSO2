package com.example.classifier.dto;

public class ResultDTO {

    private String categoryNews;
    private float calculatePriorProb;

    public ResultDTO() {
    }

    public ResultDTO(String categoryNews, float calculatePriorProb) {
        this.categoryNews = categoryNews;
        this.calculatePriorProb = calculatePriorProb;
    }

    public String getCategoryNews() {
        return categoryNews;
    }

    public void setCategoryNews(String categoryNews) {
        this.categoryNews = categoryNews;
    }

    public float getCalculatePriorProb() {
        return calculatePriorProb;
    }

    public void setCalculatePriorProb(float calculatePriorProb) {
        this.calculatePriorProb = calculatePriorProb;
    }


}
