package com.pigeonskyrace.repository;

import com.pigeonskyrace.model.Colombier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ColombierRepository extends MongoRepository<Colombier,String> {
    Optional<Colombier> findByNomColombier(String nomColombier);
}
