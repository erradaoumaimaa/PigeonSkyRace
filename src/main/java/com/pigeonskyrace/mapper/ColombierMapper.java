package com.pigeonskyrace.mapper;

import com.pigeonskyrace.dto.reponse.ColombierReponseDTO;
import com.pigeonskyrace.dto.request.ColombierRequestDTO;
import com.pigeonskyrace.model.Colombier;
import org.bson.types.ObjectId;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ColombierMapper {


    @Named("objectIdToString")
    default String objectIdToString(ObjectId objectId) {
        return objectId != null ? objectId.toHexString() : null;
    }

    @Named("stringToObjectId")
    default ObjectId stringToObjectId(String id) {
        return id != null ? new ObjectId(id) : null;
    }


    Colombier toColombier(ColombierRequestDTO colombierRequestDTO, @Context ObjectId userId);

    @Mapping(source = "user.id", target = "userId", qualifiedByName = "objectIdToString")
    @Mapping(source = "id", target = "id", qualifiedByName = "objectIdToString")
    ColombierReponseDTO toColombierResponseDTO(Colombier colombier);
}
