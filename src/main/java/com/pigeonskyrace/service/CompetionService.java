package com.pigeonskyrace.service;

import com.pigeonskyrace.dto.reponse.CompetionReponseDTO;
import com.pigeonskyrace.exception.EntityNotFoundException;
import com.pigeonskyrace.mapper.CompetionMapper;
import com.pigeonskyrace.model.Competion;
import com.pigeonskyrace.repository.CompetionRepository;
import com.pigeonskyrace.utils.CompetitionId;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompetionService {

    private final CompetionRepository competionRepository;
    private final CompetionMapper mapper;


    public Competion save(Competion competion) {

    return competionRepository.save(competion);

    }
    public List<Competion> findAll() {
        return competionRepository.findAll();
    }


    public CompetionReponseDTO getCompetitionById(String competitionId) {
        System.out.println("Looking for competition with ID: " + competitionId);

        if (!ObjectId.isValid(competitionId)) {
            throw new IllegalArgumentException("Invalid competition ID format: " + competitionId);
        }

        ObjectId objectId = new ObjectId(competitionId);

        System.out.println("Converted to ObjectId: " + objectId);

        Competion competition = competionRepository.findById(objectId)
                .orElseThrow(() -> new IllegalArgumentException("Competition not found with ID: " + competitionId));

        System.out.println("Found competition: " + competition);

        return mapper.toDto(competition);
    }






}

