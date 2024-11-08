package com.pigeonskyrace.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

@Data
public class CompetionRequestDTO {
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

    private String saisonNom;
    private double pourcentageAdmission = 25.0;
}
