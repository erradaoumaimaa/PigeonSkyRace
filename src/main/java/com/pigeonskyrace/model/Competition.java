package com.pigeonskyrace.model;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "competitions")
public class Competition {

    @MongoId
    private ObjectId id;

    @NotBlank(message = "Le nom est requis")
    @Size(max = 100, message = "Le nom ne doit pas dépasser 100 caractères")
    @Indexed(unique = true)
    private String nom;

    @NotNull(message = "La date de début est requise")
    private LocalDateTime startDate;

    @NotNull(message = "La date de fin est requise")
    private LocalDateTime endDate;

    @NotBlank(message = "Les coordonnées GPS sont requises")
    @Size(max = 100, message = "Les coordonnées GPS ne doivent pas dépasser 100 caractères")
    private String coordonnGPS;

    @Min(value = 1, message = "Le nombre de pigeons doit être au moins de 1")
    private int nbPigeons;

    private double pourcentageAdmission = 25.0;

    public Competition(ObjectId id, String nom, LocalDateTime startDate, LocalDateTime endDate, String coordonnGPS, int nbPigeons) {
        this.id = id;
        this.nom = nom;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coordonnGPS = coordonnGPS;
        this.nbPigeons = nbPigeons;
    }
    // Référence à la saison
    @DBRef
    private Saison saison;
}
