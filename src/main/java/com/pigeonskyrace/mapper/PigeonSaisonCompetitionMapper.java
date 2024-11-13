package com.pigeonskyrace.mapper;

import com.pigeonskyrace.dto.request.PigeonSaisonCompetitionRequestDTO;
import com.pigeonskyrace.dto.reponse.PigeonSaisonCompetitionResponseDTO;
import com.pigeonskyrace.model.PigeonSaisonCompetition;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class PigeonSaisonCompetitionMapper {

    public PigeonSaisonCompetition toEntity(PigeonSaisonCompetitionRequestDTO requestDTO) {
        return new PigeonSaisonCompetition(
                new ObjectId(),
                new ObjectId(requestDTO.getSaisonPigeonId()),
                new ObjectId(requestDTO.getCompetitionId())
        );
    }

    public PigeonSaisonCompetitionResponseDTO toResponseDTO(PigeonSaisonCompetition entity) {
        return new PigeonSaisonCompetitionResponseDTO(
                entity.getId().toHexString(),
                entity.getSaisonPigeonId().toHexString(),
                entity.getCompetitionId().toHexString()
        );
    }
}
