package com.pigeonskyrace.mapper;

import com.pigeonskyrace.model.Colombier;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ColombierMapper {
    ColombierMapper INSTANCE = Mappers.getMapper(ColombierMapper.class);

    // Convertit un Colombier en ObjectId
    ObjectId toObjectId(Colombier colombier);

    // Convertit un ObjectId en Colombier (en supposant que vous ayez une méthode pour récupérer un Colombier par son ID)
    default Colombier toColombier(ObjectId id) {

        return null;
    }
}
