package com.pigeonskyrace.repository;

import com.pigeonskyrace.model.PigeonSaisonCompetition;
import com.pigeonskyrace.model.Resultat;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultatRepository extends MongoRepository<Resultat, ObjectId> {
   Optional<Resultat>  findByPigeonSaisonCompetition(PigeonSaisonCompetition pigeonSaisonCompetition);
}
