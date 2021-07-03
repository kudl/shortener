package com.kudlwork.shortener.model;

public class CreateLinkResponse {
	
	private String shortenedLink;
	
	public CreateLinkResponse() {
	}
	
	public CreateLinkResponse(String shortenedLink) {
		this.shortenedLink = shortenedLink;
	}
	
	public String getShortenedLink() {
		return shortenedLink;
	}
}
