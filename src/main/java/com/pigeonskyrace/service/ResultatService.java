package com.pigeonskyrace.service;

import com.pigeonskyrace.dto.reponse.PigeonSaisonCompetitionResponseDTO;
import com.pigeonskyrace.dto.reponse.ResultatReponseDTO;
import com.pigeonskyrace.dto.request.ResultatRequestDTO;
import com.pigeonskyrace.mapper.ResultatMapper;
import com.pigeonskyrace.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ResultatService {

private final PigeonService pigeonService;
private final SaisonPigeonService  saisonPigeonService;
private final ResultatMapper mapper;
private final PigeonSaisonCompetitionService pigeonSaisonCompetitionService;

    public ResultatReponseDTO createResult(ResultatRequestDTO resultatRequestDTO ,Competion compition){

        Pigeon pigeon = pigeonService.findByNumeroBague(resultatRequestDTO.numeroBague());
        SaisonPigeon saisonPigeon= saisonPigeonService.getSaisonPigeonBySaisonIdAndPigeonId(compition.getSaison().getId(),pigeon.id());
        PigeonSaisonCompetition pigeonSaisonCompetition = pigeonSaisonCompetitionService.findBySeasonPigeonAndCompetition(competitionId,saisonPigeonId);
        Resultat resultat = mapper.toEntity(resultatRequestDTO);
        resultat.setPigeonSaisonCompetition(pigeonSaisonCompetition);
       // resultat.setDistance(calculateDistance());

    }
}
