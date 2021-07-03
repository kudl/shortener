package com.kudlwork.shortener.repository;

import com.kudlwork.shortener.repository.model.Link;
import reactor.core.publisher.Mono;

public interface LinkRepository {
	
	Mono<Link> save(Link link);

	Mono<Link> findByKey(String key);
}
