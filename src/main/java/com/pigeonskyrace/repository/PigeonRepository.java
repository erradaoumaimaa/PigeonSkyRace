package com.pigeonskyrace.repository;

import com.pigeonskyrace.model.Pigeon;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PigeonRepository extends MongoRepository<Pigeon, Long> {
}
