package com.pigeonskyrace.repository;

import com.pigeonskyrace.model.Colombier;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ColombierRepository extends MongoRepository<Colombier,String> {
}
