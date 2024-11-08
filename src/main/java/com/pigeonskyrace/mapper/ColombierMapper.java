package com.pigeonskyrace.mapper;

import com.pigeonskyrace.dto.reponse.ColombierReponseDTO;
import com.pigeonskyrace.dto.reponse.UserResponseDTO;
import com.pigeonskyrace.model.Colombier;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class ColombierMapper {

    public Colombier toColombier(UserResponseDTO.ColombierRequestDTO colombierRequestDTO, ObjectId proprietaireId) {
        if (colombierRequestDTO == null) {
            return null;
        }
        Colombier colombier = new Colombier();
        colombier.setNomColombier(colombierRequestDTO.getNomColombier());
        colombier.setCoordonneeGPS(colombierRequestDTO.getCoordonneeGPS());
        colombier.setProprietaireId(proprietaireId); // Assigner l'ID du propriétaire ici
        return colombier;
    }


    public ColombierReponseDTO toColombierResponseDTO(Colombier colombier) {
        if (colombier == null) {
            return null;
        }
        ColombierReponseDTO responseDTO = new ColombierReponseDTO();
        responseDTO.setId(colombier.getId());
        responseDTO.setNomColombier(colombier.getNomColombier());
        responseDTO.setCoordonneeGPS(colombier.getCoordonneeGPS());
        responseDTO.setProprietaireId(
                colombier.getProprietaire() != null ? colombier.getProprietaire().getId().toString() : null
        );
        return responseDTO;
    }
}