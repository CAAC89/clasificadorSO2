package com.example.classifier.dto;

public class TitleUrlDTO {
    private String title;
    private String url;

    public TitleUrlDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "{" +
                "title:'" + title + '\'' +
                ", url:'" + url + '\'' +
                '}';
    }
}
