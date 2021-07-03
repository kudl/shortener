package com.kudlwork.shortener.service;

import com.kudlwork.shortener.repository.LinkRepository;
import com.kudlwork.shortener.repository.model.Link;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LinkServiceTest {
	
	private LinkRepository linkRepository = mock(LinkRepository.class);
	private LinkService linkService = new LinkService("http://localhost:8080", linkRepository);
	
	@BeforeEach
	public void setUp() {
		when(linkRepository.save(any()))
				.thenAnswer((Answer<Mono<Link>>) invocation -> {
					Link link = (Link) invocation.getArguments()[0];
					return Mono.just(link);
				});
	}
	
	@Test
	void shortenLink() {
		StepVerifier.create(linkService.shortenLink("https://spring.io"))
				.expectNextMatches(result -> result != null && result.length() > 0
						                             && result.startsWith("http://localhost:8080"))
				.expectComplete()
				.verify();
	}
}
