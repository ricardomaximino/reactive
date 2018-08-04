package com.brasajava.reativeone.service;

import com.brasajava.reativeone.domain.entity.Lead;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LeadService {

	Mono<Lead> create(Lead lead);
	Mono<Lead> update(String id,Lead lead);
	Mono<Lead> updateWithPatch(String id, Object patchObject);
	Flux<Lead> findAllLeads();
	Mono<Lead> findLeadById(String id);
	Mono<Void> deleteLeadById(String id);
}
