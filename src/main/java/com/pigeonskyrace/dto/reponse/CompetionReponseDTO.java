package com.pigeonskyrace.dto.reponse;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CompetionReponseDTO {
    private String id;
    private String nom;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String coordonnGPS;
    private String saisonNom;
    private int nbPigeons;
    private double pourcentageAdmission = 25.0;
}
