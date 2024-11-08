package com.pigeonskyrace.repository;

import com.pigeonskyrace.model.Saison;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SaisonRepository extends MongoRepository<Saison, String> {
    Optional<Saison> findTopByNom(String nom);
}
