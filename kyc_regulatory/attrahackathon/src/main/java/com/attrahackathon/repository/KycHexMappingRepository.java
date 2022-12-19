package com.attrahackathon.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.attrahackathon.request.KycMapping;

public interface KycHexMappingRepository extends MongoRepository<KycMapping, String> {
	
}
