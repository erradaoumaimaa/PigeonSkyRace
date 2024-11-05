package com.pigeonskyrace.repository;

import com.pigeonskyrace.model.Competion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompetionRepository extends MongoRepository<Competion,String> {
}
