package com.example.demo.dto;

import java.util.ArrayList;

public class ClassifierDTO {
    private int totalNews;
    private ArrayList<CategoryNewsDTO> categoryNewsDTOArrayList;

    public int getTotalNews() {
        return totalNews;
    }

    public void setTotalNews(int totalNews) {
        this.totalNews = totalNews;
    }

    public ArrayList<CategoryNewsDTO> getCategoryNewsDTOArrayList() {
        return categoryNewsDTOArrayList;
    }

    public void setCategoryNewsDTOArrayList(ArrayList<CategoryNewsDTO> categoryNewsDTOArrayList) {
        this.categoryNewsDTOArrayList = categoryNewsDTOArrayList;
    }
}
