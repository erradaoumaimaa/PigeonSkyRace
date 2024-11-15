package com.pigeonskyrace.mapper;

import com.pigeonskyrace.dto.reponse.ColombierReponseDTO;
import com.pigeonskyrace.dto.reponse.UserResponseDTO;
import com.pigeonskyrace.model.Colombier;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface ColombierMapper {

    ColombierMapper INSTANCE = Mappers.getMapper(ColombierMapper.class);

    Colombier toColombier(UserResponseDTO.ColombierRequestDTO colombierRequestDTO, ObjectId proprietaireId);

    ColombierReponseDTO toColombierResponseDTO(Colombier colombier);
}