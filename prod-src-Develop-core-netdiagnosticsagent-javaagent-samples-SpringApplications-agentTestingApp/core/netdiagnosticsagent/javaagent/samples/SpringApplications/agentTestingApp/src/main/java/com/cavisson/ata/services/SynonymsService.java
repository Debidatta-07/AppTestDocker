package com.cavisson.ata.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.cavisson.ata.models.User;
import reactor.core.publisher.Mono;
import java.time.Duration;
import java.util.Comparator;
import java.util.stream.Stream;

//webflux service
@Service
public class SynonymsService {

	private WebClient client = WebClient.create("https://api.datamuse.com/");

	public Mono<String> getSynonym(String word) {
		final Mono<User[]> synonymResultsMono = client.get().uri("words?rel_syn=" + word).retrieve()
				.bodyToMono(User[].class).delayElement(Duration.ofSeconds(1)); // introducing small delay to show
																				// concurrency
		return synonymResultsMono.map(synonymResultList -> getBestSynonym(synonymResultList, word));
	}

	/*
	 * public Mono<String> getSynonymSentence(String sentence) { return
	 * Stream.of(sentence.split(" ")).map(this::getSynonym) // here we have a stream
	 * of Mono .reduce(Mono.just(""), (m1, m2) -> m1.zipWith(m2, (w1, w2) -> w1 +
	 * " " + w2)); // we combine the mono // with zipWith }
	 */

	private String getBestSynonym(User[] synonymResultList, String word) {
		return Stream.of(synonymResultList).filter(r -> !r.getWord().equals(word) && !r.getWord().equals(""))
				.max(Comparator.comparing(User::getScore)).map(User::getWord).orElse(word); // if no synonym found we
																							// return the word
	}
}