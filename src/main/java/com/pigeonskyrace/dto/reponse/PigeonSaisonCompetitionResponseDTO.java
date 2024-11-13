package com.pigeonskyrace.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PigeonSaisonCompetitionResponseDTO {
    private String id;
    private String saisonPigeonId;
    private String competitionId;
}
