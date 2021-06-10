package com.example.classifier.dto;

public class SendDTO {
	private String title;
	private String url;
	private String typeNew;
	public SendDTO() {
		super();
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
	public String getTypeNew() {
		return typeNew;
	}
	public void setTypeNew(String typeNew) {
		this.typeNew = typeNew;
	}
	@Override
	public String toString() {
		return "{\"title\":\""+title+"\",\"url\":\""+url+"\",\"typeNew\":\""+typeNew+"\"}";
	}

	
	
	
}
