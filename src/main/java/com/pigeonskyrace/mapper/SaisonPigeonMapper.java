package com.pigeonskyrace.mapper;
import com.pigeonskyrace.dto.reponse.SaisonPigeonResponseDTO;
import com.pigeonskyrace.dto.request.SaisonPigeonRequestDTO;
import com.pigeonskyrace.model.SaisonPigeon;
import org.bson.types.ObjectId;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SaisonPigeonMapper {

    SaisonPigeonMapper INSTANCE = Mappers.getMapper(SaisonPigeonMapper.class);

    @Mapping(target = "saisonId", expression = "java(new org.bson.types.ObjectId(saisonId))")
    SaisonPigeon toEntity(SaisonPigeonRequestDTO dto, String saisonId);

    SaisonPigeonResponseDTO toDto(SaisonPigeon saisonPigeon);
}

