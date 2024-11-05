package com.pigeonskyrace.mapper;


import com.pigeonskyrace.dto.reponse.SaisonReponseDTO;
import com.pigeonskyrace.dto.request.SaisonRequestDTO;
import com.pigeonskyrace.model.Saison;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaisonMapper {
    SaisonReponseDTO toDto(Saison saison);
    Saison toEntity(SaisonRequestDTO saisonRequestDTO);
}
