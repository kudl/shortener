package com.kudlwork.shortener.repository;

import com.kudlwork.shortener.repository.model.Link;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class RedisLinkRepositoryTest {

	@Autowired
	private RedisLinkRepository redisLinkRepository;

	@Test
	void returnSameLinkAsArgument() {
		final Link link = new Link("https://spring.io/", "aaa");
		StepVerifier.create(redisLinkRepository.save(link))
				.expectNext(link)
				.verifyComplete();
	}

	@Test
	void saveInRedis() {
		final Link link = new Link("https://spring.io", "aaa");
		StepVerifier.create(redisLinkRepository.save(link)
					.flatMap(redis -> redisLinkRepository.findByKey(link.getKey())))
				.expectNext(link)
				.verifyComplete();
	}
}
