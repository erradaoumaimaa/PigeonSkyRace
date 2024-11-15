package com.pigeonskyrace.dto.reponse;
import com.pigeonskyrace.model.Resultat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CompetionReponseDTO {
    private String id;
    private String nom;
    private double latitudeGPS;
    private double longitudeGPS;
    private String saisonNom;
    private int nbPigeons;
    private double pourcentageAdmission = 25.0;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


}
