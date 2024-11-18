package com.pigeonskyrace.repository;

import com.pigeonskyrace.model.Resultat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultatRepository extends MongoRepository<Resultat, String> {
    List<Resultat> findByPigeonSaisonCompetition_Competition_Id(String competitionId);
}
