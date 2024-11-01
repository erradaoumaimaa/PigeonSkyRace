package com.pigeonskyrace.mapper;

import com.pigeonskyrace.dto.reponse.PigeonResponseDTO;
import com.pigeonskyrace.dto.request.PigeonRequestDTO;
import com.pigeonskyrace.model.Colombier;
import com.pigeonskyrace.model.Pigeon;
import com.pigeonskyrace.service.ColombierService;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring")
public abstract class PigeonMapper {

    @Autowired
    private ColombierService colombierService;

    @Mapping(target = "sexe", source = "sexe")
    public abstract Pigeon toPigeon(PigeonRequestDTO pigeonRequestDTO);

    public abstract PigeonResponseDTO toPigeonResponseDTO(Pigeon pigeon);

    // Méthode de conversion personnalisée pour String -> Colombier
    protected Colombier map(String colombierId) {
        return colombierService.findByNomColombier(colombierId)
                .orElse(null);
    }

    // Méthode de conversion personnalisée pour Colombier -> String
    protected String map(Colombier colombier) {
        return colombier != null ? colombier.getNomColombier() : null;
    }

    String map(ObjectId value) {
        return value != null ? value.toString() : null;
    }
}
