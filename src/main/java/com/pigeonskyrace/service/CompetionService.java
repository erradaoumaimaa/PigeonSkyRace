package com.pigeonskyrace.service;

import com.pigeonskyrace.dto.reponse.CompetionReponseDTO;
import com.pigeonskyrace.exception.EntityNotFoundException;
import com.pigeonskyrace.mapper.CompetionMapper;
import com.pigeonskyrace.model.Competion;
import com.pigeonskyrace.repository.CompetionRepository;
import com.pigeonskyrace.utils.CompetitionId;
import lombok.RequiredArgsConstructor;
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


    public CompetionReponseDTO getCompetitionid(CompetitionId competitionId) {
        System.out.println("Searching competition with ID: " + competitionId.toString());
        Optional<Competion> competition = competionRepository.findById(competitionId.toString());

        if (competition.isPresent()) {
            System.out.println("Competition found: " + competition.get());
            return mapper.toDto(competition.get());
        } else {
            System.out.println("Competition not found with ID: " + competitionId.toString());
            throw new EntityNotFoundException("Competition not found for ID: " + competitionId.toString());
        }
    }





}

