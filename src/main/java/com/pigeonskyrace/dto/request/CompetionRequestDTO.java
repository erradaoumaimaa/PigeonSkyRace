package com.pigeonskyrace.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

public class CompetionRequestDTO {

    @NotBlank(message = "Le nom est requis")
    @Size(max = 100, message = "Le nom ne doit pas dépasser 100 caractères")
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

    private String saisonId;

    public @NotBlank(message = "Le nom est requis") @Size(max = 100, message = "Le nom ne doit pas dépasser 100 caractères") String getNom() {
        return nom;
    }

    public void setNom(@NotBlank(message = "Le nom est requis") @Size(max = 100, message = "Le nom ne doit pas dépasser 100 caractères") String nom) {
        this.nom = nom;
    }

    public @NotNull(message = "La date de fin est requise") LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(@NotNull(message = "La date de fin est requise") LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public @NotNull(message = "La date de début est requise") LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(@NotNull(message = "La date de début est requise") LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public @NotBlank(message = "Les coordonnées GPS sont requises") @Size(max = 100, message = "Les coordonnées GPS ne doivent pas dépasser 100 caractères") String getCoordonnGPS() {
        return coordonnGPS;
    }

    public void setCoordonnGPS(@NotBlank(message = "Les coordonnées GPS sont requises") @Size(max = 100, message = "Les coordonnées GPS ne doivent pas dépasser 100 caractères") String coordonnGPS) {
        this.coordonnGPS = coordonnGPS;
    }

    public double getPourcentageAdmission() {
        return pourcentageAdmission;
    }

    public void setPourcentageAdmission(double pourcentageAdmission) {
        this.pourcentageAdmission = pourcentageAdmission;
    }

    @Min(value = 1, message = "Le nombre de pigeons doit être au moins de 1")
    public int getNbPigeons() {
        return nbPigeons;
    }

    public void setNbPigeons(@Min(value = 1, message = "Le nombre de pigeons doit être au moins de 1") int nbPigeons) {
        this.nbPigeons = nbPigeons;
    }

    public String getSaisonId() {
        return saisonId;
    }

    public void setSaisonId(String saisonId) {
        this.saisonId = saisonId;
    }


    public CompetionRequestDTO() {
    }

    public CompetionRequestDTO(String nom, LocalDateTime startDate, LocalDateTime endDate, String coordonnGPS, int nbPigeons, String saisonId, double pourcentageAdmission) {
        this.nom = nom;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coordonnGPS = coordonnGPS;
        this.nbPigeons = nbPigeons;
        this.saisonId = saisonId;
        this.pourcentageAdmission = pourcentageAdmission;
    }
}
