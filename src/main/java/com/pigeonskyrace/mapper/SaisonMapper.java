package com.pigeonskyrace.mapper;


import com.pigeonskyrace.dto.reponse.SaisonReponseDTO;
import com.pigeonskyrace.dto.request.SaisonRequestDTO;
import com.pigeonskyrace.model.Saison;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SaisonMapper {

    public SaisonReponseDTO toDto(Saison saison) {
        if (saison == null) {
            return null;
        }

        SaisonReponseDTO dto = new SaisonReponseDTO();
        dto.setId(objectIdToString(saison.getId()));
        dto.setDate(saison.getDate());
        dto.setNom(saison.getNom());
        dto.setDescription(saison.getDescription());

        dto.setCompetions(null);

        return dto;
    }


    public Saison toEntity(SaisonRequestDTO saisonRequestDTO) {
        if (saisonRequestDTO == null) {
            return null;
        }

        Saison saison = new Saison();
        saison.setDate(saisonRequestDTO.getDate());
        saison.setNom(saisonRequestDTO.getNom());
        saison.setDescription(saisonRequestDTO.getDescription());

        saison.setCompetions(new ArrayList<>());

        return saison;
    }

    // Méthode de mappage personnalisée pour convertir ObjectId en String
    private String objectIdToString(ObjectId objectId) {
        return objectId != null ? objectId.toHexString() : null;
    }

    // Méthode de mappage personnalisée pour convertir String en ObjectId
    private ObjectId stringToObjectId(String string) {
        return string != null ? new ObjectId(string) : null;
    }
}