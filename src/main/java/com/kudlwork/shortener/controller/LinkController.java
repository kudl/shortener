package com.kudlwork.shortener.controller;

import com.kudlwork.shortener.model.CreateLinkRequest;
import com.kudlwork.shortener.model.CreateLinkResponse;
import com.kudlwork.shortener.service.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class LinkController {
	
	private final LinkService linkService;
	
	public LinkController(LinkService linkService) {
		this.linkService = linkService;
	}

	@GetMapping("/{key}")
	public Mono<ResponseEntity<Object>> getLink(@PathVariable String key) {
		return linkService.getLinkByKey(key)
				.map(link -> ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
						.header("Location", link.getOriginalKey())
						.build())
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/link")
	public Mono<CreateLinkResponse> create(@RequestBody CreateLinkRequest createLinkRequest) {
		return linkService.shortenLink(createLinkRequest.getLink())
				       .map(CreateLinkResponse::new);
	}
}
