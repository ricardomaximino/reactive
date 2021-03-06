package com.brasajava.reativeone.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.brasajava.reativeone.common.util.LeadEventBuilder;
import com.brasajava.reativeone.domain.entity.Event;
import com.brasajava.reativeone.domain.entity.Lead;
import com.brasajava.reativeone.domain.repository.LeadRepository;
import com.brasajava.reativeone.service.LeadService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class LeadServiceImpl implements LeadService{

	private LeadRepository repository;

	private LeadEventBuilder eventBuilder;
	public LeadServiceImpl(LeadRepository repository, LeadEventBuilder eventBuilder) {
		this.repository = repository;
		this.eventBuilder = eventBuilder;
	}
	@Override
	public Mono<Lead> create(Lead lead, String operator) {
		return repository.save(onCreate(lead,operator));
	}

	@Override
	public Mono<Lead> update(String id, Lead lead, String operator) {
		return repository.findById(id).flatMap(l -> {
			lead.setCreateDateTime(l.getCreateDateTime());
			return repository.save(onUpdate(lead, operator));
			});
	}
	@Override
	public Mono<Lead> updateWithPatch(String id, Object patchObject, String operator) {
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

	private Lead onCreate(Lead lead,String operator) {
		LocalDateTime dateTime = LocalDateTime.now();
		lead.setCreateDateTime(dateTime);
		lead.setUpdateDateTime(dateTime);
		lead.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		return lead.create(eventBuilder.createEvent(null, Event.CREATE_OPERATION, operator, dateTime));
	}
	
	private Lead onUpdate(Lead lead, String operator) {
		LocalDateTime dateTime = LocalDateTime.now();
		lead.setUpdateDateTime(dateTime);
		return lead.updatd(eventBuilder.createEvent(null, Event.UPDATE_OPERATION, operator, dateTime));
	}
}
