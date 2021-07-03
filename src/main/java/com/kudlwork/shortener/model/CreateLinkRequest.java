package com.kudlwork.shortener.model;

public class CreateLinkRequest {
	
	private String link;
	
	public CreateLinkRequest() {
	}
	
	public CreateLinkRequest(String link) {
		this.link = link;
	}
	
	public String getLink() {
		return link;
	}
}
