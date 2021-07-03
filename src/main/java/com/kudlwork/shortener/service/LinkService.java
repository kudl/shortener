package com.kudlwork.shortener.service;

import com.kudlwork.shortener.repository.LinkRepository;
import com.kudlwork.shortener.repository.model.Link;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LinkService {
	
	
	private final String baseUrl;
	private final LinkRepository linkRepository;
	
	public LinkService(@Value("${app.base-url}") String baseUrl, LinkRepository linkRepository) {
		this.baseUrl = baseUrl;
		this.linkRepository = linkRepository;
	}
	
	public Mono<String> shortenLink(String link) {
		String randomKey = RandomStringUtils.randomAlphabetic(6);
		
		return linkRepository.save(new Link(link, randomKey))
				       .map(result -> baseUrl + result.getKey());
	}

	public Mono<Link> getLinkByKey(String key) {
		return linkRepository.findByKey(key);
	}
}
