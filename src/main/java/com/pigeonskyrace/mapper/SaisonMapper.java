package com.pigeonskyrace.mapper;


import com.pigeonskyrace.dto.reponse.SaisonReponseDTO;
import com.pigeonskyrace.dto.request.SaisonRequestDTO;
import com.pigeonskyrace.model.Saison;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface SaisonMapper {
    @Mapping(target = "id", source = "id", qualifiedByName = "objectIdToString")
    @Mapping(target = "competions", ignore = true)
    SaisonReponseDTO toDto(Saison saison);

    default Saison toEntity(SaisonRequestDTO saisonRequestDTO) {
        if (saisonRequestDTO == null) {
            return null;
        }

        Saison saison = new Saison();
        saison.setDate(saisonRequestDTO.getDate());
        saison.setNom(saisonRequestDTO.getNom());
        saison.setDescription(saisonRequestDTO.getDescription());
        System.out.println("Mapped Saison: " + saison);

        return saison;
    }

    // Méthode de mappage personnalisée pour convertir ObjectId en String
    @Named("objectIdToString")
    default String map(ObjectId value) {
        return value != null ? value.toHexString() : null; // Convertit l'ObjectId en String
    }

    // Méthode de mappage personnalisée pour convertir String en ObjectId
    default ObjectId map(String value) {
        return value != null ? new ObjectId(value) : null; // Convertit le String en ObjectId
    }
}