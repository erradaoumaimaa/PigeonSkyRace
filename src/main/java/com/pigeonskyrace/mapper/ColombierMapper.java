package com.pigeonskyrace.mapper;

import com.pigeonskyrace.dto.reponse.ColombierReponseDTO;
import com.pigeonskyrace.dto.reponse.UserReponseDTO;
import com.pigeonskyrace.model.Colombier;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ColombierMapper {

    Colombier toColombier(UserReponseDTO.ColombierRequestDTO colombierRequestDTO);
   @Mapping(target = "proprietaireId", source = "proprietaire.id")
   //@Mapping(target = "proprietaireId", source = "proprietaireId")
    ColombierReponseDTO toColombierResponseDTO(Colombier colombier);


    default String map(ObjectId value) {
        return value != null ? value.toString() : null;
    }
}
