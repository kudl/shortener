package com.kudlwork.shortener.controller;

import com.kudlwork.shortener.model.CreateLinkRequest;
import com.kudlwork.shortener.repository.model.Link;
import com.kudlwork.shortener.service.LinkService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = LinkController.class)
class LinkControllerTest {
	
	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private LinkService linkService;

	@Test
	void redirectsToOriginalLink() {
		when(linkService.getLinkByKey("aaa"))
				.thenReturn(Mono.just(new Link("https://spring.io", "aaa")));

		webTestClient.get()
				.uri("/aaa")
				.exchange()
				.expectStatus()
				.isPermanentRedirect()
				.expectHeader()
				.value("Location", location -> assertThat(location).isEqualTo("https://spring.io"));
	}
	
	@Test
	void shortensLink() {
		when(linkService.shortenLink("https://spring.io"))
				.thenReturn(Mono.just("http://localhost:8080/aaa"));
		
		Mono<CreateLinkRequest> linkRequest = Mono.just(new CreateLinkRequest("https://spring.io"));
		
		webTestClient.post()
				.uri("/link")
				.contentType(MediaType.APPLICATION_JSON)
				.body(linkRequest, CreateLinkRequest.class)
				.exchange()
				.expectStatus()
				.is2xxSuccessful()
				.expectBody()
				.jsonPath("$.shortenedLink")
				.value(val -> assertThat(val).isEqualTo("http://localhost:8080/aaa"));
	}
}
