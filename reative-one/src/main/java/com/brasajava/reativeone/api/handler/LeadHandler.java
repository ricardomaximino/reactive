package com.brasajava.reativeone.api.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.brasajava.reativeone.domain.repository.LeadRepository;

import reactor.core.publisher.Mono;

public class LeadHandler {

	private LeadRepository repository;
	
	public LeadHandler(LeadRepository repository) {
		this.repository = repository;
	}
	
	public Mono<ServerResponse> hello(ServerRequest request){
		return ServerResponse.ok().body(BodyInserters.fromObject("Hello World from handler"));
	}
}
