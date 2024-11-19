package com.pigeonskyrace.controller;

import com.pigeonskyrace.dto.request.PigeonSaisonCompetitionRequestDTO;
import com.pigeonskyrace.dto.reponse.PigeonSaisonCompetitionResponseDTO;
import com.pigeonskyrace.mapper.PigeonSaisonCompetitionMapper;
import com.pigeonskyrace.model.PigeonSaisonCompetition;
import com.pigeonskyrace.service.PigeonSaisonCompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/pigeon-season-competitions")
public class PigeonSeasonCompetitionController {

    @Autowired
    private PigeonSaisonCompetitionService service;
    private final PigeonSaisonCompetitionMapper pigeonSaisonCompetitionMapper;

    @PostMapping("/{competitionId}")
    public ResponseEntity<PigeonSaisonCompetitionResponseDTO> registerPigeonInCompetition(
            @PathVariable String competitionId,
            @Valid @RequestBody PigeonSaisonCompetitionRequestDTO requestDTO) {

        // Assigner l'ID de la compétition au DTO (le cas échéant)
        requestDTO.setCompetitionId(competitionId);

        // Mapper le requestDTO en entité PigeonSaisonCompetition
        PigeonSaisonCompetition pigeonSaisonCompetition = pigeonSaisonCompetitionMapper.toEntity(requestDTO);

        // Appeler le service avec l'entité
        PigeonSaisonCompetition savedEntity = service.registerPigeonInCompetition(pigeonSaisonCompetition);

        // Mapper l'entité enregistrée en DTO de réponse
        PigeonSaisonCompetitionResponseDTO responseDTO = pigeonSaisonCompetitionMapper.toResponseDTO(savedEntity);

        return ResponseEntity.ok(responseDTO);
    }

}
