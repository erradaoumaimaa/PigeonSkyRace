package com.pigeonskyrace.service;

import com.pigeonskyrace.dto.request.PigeonSaisonCompetitionRequestDTO;
import com.pigeonskyrace.dto.reponse.PigeonSaisonCompetitionResponseDTO;
import com.pigeonskyrace.exception.EntityNotFoundException;
import com.pigeonskyrace.mapper.PigeonSaisonCompetitionMapper;
import com.pigeonskyrace.model.Competion;
import lombok.extern.slf4j.Slf4j;
import com.pigeonskyrace.model.PigeonSaisonCompetition;
import com.pigeonskyrace.model.SaisonPigeon;
import com.pigeonskyrace.repository.PigeonSaisonCompetitionRepository;
import com.pigeonskyrace.utils.CompetitionId;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PigeonSaisonCompetitionService {

    @Autowired
    private PigeonSaisonCompetitionRepository repository;

    @Autowired
    private PigeonSaisonCompetitionMapper mapper;

    public PigeonSaisonCompetitionResponseDTO registerPigeonInCompetition(PigeonSaisonCompetitionRequestDTO requestDTO) {
        ObjectId saisonPigeonId = new ObjectId(requestDTO.getSaisonPigeonId());
        ObjectId competitionId = new ObjectId(requestDTO.getCompetitionId());

        if (repository.existsBySaisonPigeonIdAndCompetitionId(saisonPigeonId, competitionId)) {
            throw new IllegalStateException("Ce pigeon est déjà inscrit dans cette compétition.");
        }

        PigeonSaisonCompetition entity = mapper.toEntity(requestDTO);
        PigeonSaisonCompetition savedEntity = repository.save(entity);

        return mapper.toResponseDTO(savedEntity);
    }
    public Optional<PigeonSaisonCompetition> findBySeasonPigeonAndCompetition(SaisonPigeon saisonPigeonId, Competion competitionId) {
        try {
            return repository.findBySaisonPigeonIdAndCompetitionId(saisonPigeonId, competitionId);
        } catch (Exception e) {
            throw new EntityNotFoundException("No matching PigeonSaisonCompetition found for the given IDs.");
        }
    }




}

