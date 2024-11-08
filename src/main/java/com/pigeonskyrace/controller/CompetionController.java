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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/competion")
public class CompetionController {

    @Autowired
    private CompetionService competionService;

    @Autowired
    private SaisonService  saisonService;

    @Autowired

    private CompetionMapper competionMapper;

    @PostMapping
    public ResponseEntity<CompetionReponseDTO> createCompetion(@Valid @RequestBody CompetionRequestDTO competionRequestDTO) {
        Competion competion = competionMapper.toEntity(competionRequestDTO);

        // Rechercher la saison par son nom
        Saison saison = saisonService.findByNom(competionRequestDTO.getSaisonNom())
                .orElseThrow(() -> new RuntimeException("Saison non trouvée avec le nom : " + competionRequestDTO.getSaisonNom()));
        competion.setSaison(saison);

        Competion savedCompetion = competionService.save(competion);
        CompetionReponseDTO responseDto = competionMapper.toDto(savedCompetion);

        return ResponseEntity.ok(responseDto);
    }

}
