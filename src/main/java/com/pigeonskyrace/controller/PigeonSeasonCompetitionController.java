package com.pigeonskyrace.controller;

import com.pigeonskyrace.dto.request.PigeonSaisonCompetitionRequestDTO;
import com.pigeonskyrace.dto.reponse.PigeonSaisonCompetitionResponseDTO;
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

    @PostMapping("/{competitionId}")
    public ResponseEntity<PigeonSaisonCompetitionResponseDTO> registerPigeonInCompetition(
            @PathVariable String competitionId,
            @Valid @RequestBody PigeonSaisonCompetitionRequestDTO requestDTO) {

        requestDTO.setCompetitionId(competitionId);

        PigeonSaisonCompetitionResponseDTO responseDTO = service.registerPigeonInCompetition(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
