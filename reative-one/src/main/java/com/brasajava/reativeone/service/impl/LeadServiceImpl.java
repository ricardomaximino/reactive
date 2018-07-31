package com.brasajava.reativeone.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.brasajava.reativeone.domain.entity.Lead;
import com.brasajava.reativeone.domain.repository.LeadRepository;
import com.brasajava.reativeone.service.LeadService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class LeadServiceImpl implements LeadService{

	private LeadRepository repository;

	public LeadServiceImpl(LeadRepository repository) {
		this.repository = repository;
	}
	@Override
	public Mono<Lead> create(Lead lead) {
		return repository.save(onCreate(lead));
	}

	@Override
	public Mono<Lead> update(String id, Lead lead) {
		return repository.findById(id).flatMap(l -> {
			lead.setCreateDateTime(l.getCreateDateTime());
			return repository.save(onUpdate(lead));
			});
	}
	@Override
	public Mono<Lead> updateWithPatch(String id, Object patchObject) {
		return null;
	}

	@Override
	public Flux<Lead> findAllLeads() {
		return repository.findAll();
	}

	@Override
	public Mono<Lead> findLeadById(String id) {
		return repository.findById(id);
	}

	@Override
	public Mono<Void> deleteLeadById(String id) {
		return repository.findById(id).flatMap(l -> repository.delete(l));
	}

	private Lead onCreate(Lead lead) {
		LocalDateTime dateTime = LocalDateTime.now();
		lead.setCreateDateTime(dateTime);
		lead.setUpdateDateTime(dateTime);
		lead.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		return lead;
	}
	
	private Lead onUpdate(Lead lead) {
		LocalDateTime dateTime = LocalDateTime.now();
		lead.setUpdateDateTime(dateTime);
		return lead;
	}
}