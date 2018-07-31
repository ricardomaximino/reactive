package com.brasajava.reativeone.api.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.brasajava.reativeone.api.handler.LeadHandler;
import com.brasajava.reativeone.domain.repository.LeadRepository;

@Configuration
public class LeadRouter {

	@Bean
	public LeadHandler leadHandler(LeadRepository repository) {
		return new LeadHandler(repository);
	}

	@Bean
	public RouterFunction<ServerResponse> router(LeadHandler handler) {
		return RouterFunctions.nest(RequestPredicates.path("/handler/leads"),
				RouterFunctions.route(RequestPredicates.GET("/hello"), handler::hello));
	}
}
