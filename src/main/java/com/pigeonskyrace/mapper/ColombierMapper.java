package com.pigeonskyrace.mapper;

import com.pigeonskyrace.dto.reponse.ColombierReponseDTO;
import com.pigeonskyrace.dto.reponse.UserResponseDTO;
import com.pigeonskyrace.model.Colombier;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ColombierMapper {

    Colombier toColombier(UserResponseDTO.ColombierRequestDTO colombierRequestDTO);
  // @Mapping(target = "proprietaireId", source = "proprietaire.id")
    ColombierReponseDTO toColombierResponseDTO(Colombier colombier);


    default String map(ObjectId value) {
        return value != null ? value.toString() : null;
    }
}
