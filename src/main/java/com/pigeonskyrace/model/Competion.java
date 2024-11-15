package com.pigeonskyrace.model;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Document(collection = "competions")
public class Competion {

    @MongoId
    private String id;

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

    private LocalDateTime dateDébut;
    private LocalDateTime dateFin;


    // Référence à la saison
    @DBRef
    private Saison saison;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getCoordonnGPS() {
        return coordonnGPS;
    }

    public void setCoordonnGPS(String coordonnGPS) {
        this.coordonnGPS = coordonnGPS;
    }

    public int getNbPigeons() {
        return nbPigeons;
    }

    public void setNbPigeons(int nbPigeons) {
        this.nbPigeons = nbPigeons;
    }

    public double getPourcentageAdmission() {
        return pourcentageAdmission;
    }

    public void setPourcentageAdmission(double pourcentageAdmission) {
        this.pourcentageAdmission = pourcentageAdmission;
    }

    public Saison getSaison() {
        return saison;
    }

    public void setSaison(Saison saison) {
        this.saison = saison;
    }

    public Competion() {


    }

    public LocalDateTime getDateDébut() {
        return dateDébut;
    }

    public void setDateDébut(LocalDateTime dateDébut) {
        this.dateDébut = dateDébut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public Competion(String id, String nom, LocalDateTime startDate, LocalDateTime endDate, String coordonnGPS, int nbPigeons, double pourcentageAdmission, Saison saison, LocalDateTime dateDébut, LocalDateTime dateFin) {
        this.id = id;
        this.nom = nom;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coordonnGPS = coordonnGPS;
        this.nbPigeons = nbPigeons;
        this.pourcentageAdmission = pourcentageAdmission;
        this.saison = saison;
        this.dateDébut=dateDébut;
        this.dateFin=dateFin;
    }
}
