package com.pigeonskyrace.controller;


import com.pigeonskyrace.dto.reponse.CompetionReponseDTO;
import com.pigeonskyrace.dto.request.CompetionRequestDTO;
import com.pigeonskyrace.mapper.CompetionMapper;
import com.pigeonskyrace.model.Competion;
import com.pigeonskyrace.model.Saison;
import com.pigeonskyrace.service.CompetionService;
import com.pigeonskyrace.service.SaisonService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/competion")
public class CompetionController {


    private final CompetionMapper competionMapper = new CompetionMapper();
    private final CompetionService competionService;

    @Autowired
    private SaisonService  saisonService;

    @Autowired
    public CompetionController(CompetionService competionService) {
        this.competionService = competionService;
    }
    // Endpoint pour créer une compétition
    @PostMapping("/competion")
    public ResponseEntity<CompetionReponseDTO> createCompetion(@RequestBody CompetionRequestDTO competionRequestDTO) {
        // Convert CompetionRequestDTO to Competion entity
        Competion competion = competionMapper.toEntity(competionRequestDTO);

        // Rechercher la saison par son nom
        Saison saison = saisonService.findByNom(competionRequestDTO.getSaisonNom())
                .orElseThrow(() -> new RuntimeException("Saison non trouvée avec le nom : " + competionRequestDTO.getSaisonNom()));

        // Associer la compétition à la saison
        competion.setSaison(saison);

        // Sauvegarder la compétition
        Competion savedCompetion = competionService.save(competion);

        // Ajouter la compétition à la liste des compétitions de la saison
        saison.getCompetions().add(savedCompetion);

        // Sauvegarder la saison avec la compétition ajoutée
        saisonService.save(saison);

        // Convert the saved Competion entity to CompetionReponseDTO
        CompetionReponseDTO responseDto = competionMapper.toDto(savedCompetion);

        // Return the created Competion with a 201 Created status
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }


    @GetMapping("/competions")
    public ResponseEntity<List<CompetionReponseDTO>> getAllCompetions() {
        List<Competion> competions = competionService.findAll();
        List<CompetionReponseDTO> competionsDTO = competions.stream()
                .map(competionMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(competionsDTO);
    }
}



