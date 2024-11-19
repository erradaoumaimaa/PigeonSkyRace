package com.pigeonskyrace.repository;

import com.pigeonskyrace.model.Competion;
import com.pigeonskyrace.model.PigeonSaisonCompetition;
import com.pigeonskyrace.model.SaisonPigeon;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PigeonSaisonCompetitionRepository extends MongoRepository<PigeonSaisonCompetition, ObjectId> {
    boolean existsBySaisonPigeonIdAndCompetitionId(ObjectId saisonPigeonId, ObjectId competitionId);
    Optional<PigeonSaisonCompetition> findBySaisonPigeonIdAndCompetitionId(ObjectId saisonPigeonId, ObjectId competitionId);
}