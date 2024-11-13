package com.pigeonskyrace.repository;

import com.pigeonskyrace.model.PigeonSaisonCompetition;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PigeonSaisonCompetitionRepository extends MongoRepository<PigeonSaisonCompetition, ObjectId> {
    boolean existsBySaisonPigeonIdAndCompetitionId(ObjectId saisonPigeonId, ObjectId competitionId);
}