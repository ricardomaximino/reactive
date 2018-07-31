package com.brasajava.reativeone.domain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.brasajava.reativeone.domain.entity.Lead;

public interface LeadRepository extends ReactiveMongoRepository<Lead, String>{

}
