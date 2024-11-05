package com.pigeonskyrace.repository;

import com.pigeonskyrace.model.Saison;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SaisonRepository extends MongoRepository<Saison, String> {
}
