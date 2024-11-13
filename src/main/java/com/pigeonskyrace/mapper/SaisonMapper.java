package com.pigeonskyrace.mapper;

import com.pigeonskyrace.dto.reponse.SaisonReponseDTO;
import com.pigeonskyrace.dto.reponse.CompetionReponseDTO;  // Import CompetionReponseDTO
import com.pigeonskyrace.dto.request.SaisonRequestDTO;
import com.pigeonskyrace.model.Saison;
import com.pigeonskyrace.model.Competion;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SaisonMapper {

    // Mapping from Saison to SaisonReponseDTO, including Competions
    public SaisonReponseDTO toDto(Saison saison) {
        if (saison == null) {
            return null;
        }

        SaisonReponseDTO dto = new SaisonReponseDTO();
        dto.setId(objectIdToString(saison.getId()));
        dto.setDate(saison.getDate());
        dto.setNom(saison.getNom());
        dto.setDescription(saison.getDescription());

        // Convert each Competion object to CompetionReponseDTO and add to the list
        if (saison.getCompetions() != null) {
            List<CompetionReponseDTO> competionsDto = saison.getCompetions().stream()
                    .map(this::competionToDto)  // Convert each Competion to CompetionReponseDTO
                    .collect(Collectors.toList());
            dto.setCompetions(competionsDto);
        }

        return dto;
    }

    // Mapping from SaisonRequestDTO to Saison
    public Saison toEntity(SaisonRequestDTO saisonRequestDTO) {
        if (saisonRequestDTO == null) {
            return null;
        }

        Saison saison = new Saison();
        saison.setDate(saisonRequestDTO.getDate());
        saison.setNom(saisonRequestDTO.getNom());
        saison.setDescription(saisonRequestDTO.getDescription());

        // Initialize an empty list for Competions
        saison.setCompetions(new ArrayList<>());

        return saison;
    }

    // Mapping a Competion object to CompetionReponseDTO
    private CompetionReponseDTO competionToDto(Competion competion) {
        if (competion == null) {
            return null;
        }

        CompetionReponseDTO competionDto = new CompetionReponseDTO();
        competionDto.setId((competion.getId()));
        competionDto.setNom(competion.getNom());
        competionDto.setStartDate(competion.getStartDate());
        competionDto.setEndDate(competion.getEndDate());
        competionDto.setCoordonnGPS(competion.getCoordonnGPS());
        competionDto.setNbPigeons(competion.getNbPigeons());
        competionDto.setPourcentageAdmission(competion.getPourcentageAdmission());
        competionDto.setSaisonNom(competion.getSaison() != null ? competion.getSaison().getNom() : null);

        return competionDto;
    }

    // Method to convert ObjectId to String
    private String objectIdToString(ObjectId objectId) {
        return objectId != null ? objectId.toHexString() : null;
    }

}
