package com.attrahackathon.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.attrahackathon.request.KycRequest;

public interface KycRepository extends MongoRepository<KycRequest, String> {
	
	
}
