package com.kudlwork.shortener.repository.model;

import java.util.Objects;

public class Link {
	
	private String originalKey;
	private String key;
	
	public Link() {
	}
	
	public Link(String originalKey, String key) {
		this.originalKey = originalKey;
		this.key = key;
	}
	
	public String getOriginalKey() {
		return originalKey;
	}
	
	public String getKey() {
		return key;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Link link = (Link) o;
		return Objects.equals(originalKey, link.originalKey) && Objects.equals(key, link.key);
	}

	@Override
	public int hashCode() {
		return Objects.hash(originalKey, key);
	}

	@Override
	public String toString() {
		return "Link{" +
				"originalKey='" + originalKey + '\'' +
				", key='" + key + '\'' +
				'}';
	}
}
