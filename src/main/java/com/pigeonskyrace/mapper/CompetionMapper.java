package com.pigeonskyrace.mapper;

import com.pigeonskyrace.dto.reponse.CompetionReponseDTO;
import com.pigeonskyrace.dto.request.CompetionRequestDTO;
import com.pigeonskyrace.model.Competion;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel ="spring")
public interface CompetionMapper  {


    CompetionReponseDTO toDto(Competion competition);
    Competion toEntity(CompetionRequestDTO competitionRequestDTO);


    default String map(ObjectId value) {
        return value != null ? value.toString() : null;
    }
}
