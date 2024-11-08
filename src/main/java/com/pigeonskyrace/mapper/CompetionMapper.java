package com.pigeonskyrace.mapper;

import com.pigeonskyrace.dto.reponse.CompetionReponseDTO;
import com.pigeonskyrace.dto.request.CompetionRequestDTO;
import com.pigeonskyrace.model.Competion;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class CompetionMapper {

    // Méthode de mappage de Competion vers CompetionReponseDTO
    public CompetionReponseDTO toDto(Competion competition) {
        if (competition == null) {
            return null;
        }

        CompetionReponseDTO dto = new CompetionReponseDTO();
        dto.setId(competition.getId() != null ? competition.getId().toString() : null);
        dto.setNom(competition.getNom());
        dto.setStartDate(competition.getStartDate());
        dto.setEndDate(competition.getEndDate());
        dto.setCoordonnGPS(competition.getCoordonnGPS());
        dto.setNbPigeons(competition.getNbPigeons());
        dto.setPourcentageAdmission(competition.getPourcentageAdmission() != 0.0 ? competition.getPourcentageAdmission() : 25.0);
        return dto;
    }

    // Méthode de mappage de CompetionRequestDTO vers Competion
    public Competion toEntity(CompetionRequestDTO competitionRequestDTO) {
        if (competitionRequestDTO == null) {
            return null;
        }

        Competion competition = new Competion();
        competition.setNom(competitionRequestDTO.getNom());
        competition.setStartDate(competitionRequestDTO.getStartDate());
        competition.setEndDate(competitionRequestDTO.getEndDate());
        competition.setCoordonnGPS(competitionRequestDTO.getCoordonnGPS());
        competition.setNbPigeons(competitionRequestDTO.getNbPigeons());
        competition.setPourcentageAdmission(25.0);
        return competition;
    }

    // Méthode de conversion de ObjectId en String
    String map(ObjectId value) {
        return value != null ? value.toString() : null;
    }
}
