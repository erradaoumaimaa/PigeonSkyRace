package com.pigeonskyrace.repository;

import com.pigeonskyrace.model.SaisonPigeon;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SaisonPigeonRepository extends MongoRepository<SaisonPigeon, String> {


}
