package com.pigeonskyrace.controller;


import com.pigeonskyrace.dto.reponse.SaisonReponseDTO;
import com.pigeonskyrace.dto.request.SaisonRequestDTO;
import com.pigeonskyrace.mapper.SaisonMapper;
import com.pigeonskyrace.model.Saison;
import com.pigeonskyrace.service.CompetionService;
import com.pigeonskyrace.service.SaisonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/saisons")
public class SaisonController {

    @Autowired
    private SaisonService saisonService;

    @Autowired
    private SaisonMapper saisonMapper;
    @Autowired
    private CompetionService competionService;


    @PostMapping
    public ResponseEntity<SaisonReponseDTO> createSaison(@RequestBody @Valid SaisonRequestDTO saisonRequestDTO) {

        Saison saison = saisonMapper.toEntity(saisonRequestDTO);

        System.out.println("Mapped Saison: " + saison);

        Saison savedSaison = saisonService.save(saison);

        SaisonReponseDTO responseDTO = saisonMapper.toDto(savedSaison);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<SaisonReponseDTO>> getAllSaisons() {
        List<Saison> saisons = saisonService.findAll();
        List<SaisonReponseDTO> responseDTOs = saisons.stream()
                .map(saisonMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }
}

