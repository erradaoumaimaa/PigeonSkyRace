package com.pigeonskyrace.controller;
import com.pigeonskyrace.dto.reponse.ColombierReponseDTO;
import com.pigeonskyrace.dto.reponse.PigeonResponseDTO;
import com.pigeonskyrace.dto.request.ColombierRequestDTO;
import com.pigeonskyrace.mapper.ColombierMapper;
import com.pigeonskyrace.model.Colombier;
import com.pigeonskyrace.model.Pigeon;
import com.pigeonskyrace.service.ColombierService;
import com.pigeonskyrace.service.PigeonService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/colombiers")
@RequiredArgsConstructor
public class ColombierController {

    private final ColombierService colombierService;
    private final ColombierMapper colombierMapper;
    private final PigeonService pigeonService;

    @PostMapping("")
    public ResponseEntity<ColombierReponseDTO> createColombier(
            @RequestBody @Valid com.pigeonskyrace.dto.request.ColombierRequestDTO colombierRequestDTO,
            HttpSession session) {

        ObjectId userId = (ObjectId) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        ColombierReponseDTO responseDTO = colombierService.save(colombierRequestDTO, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }


    @GetMapping("")
    public ResponseEntity<List<ColombierReponseDTO>> getAllColombiers() {
        List<Colombier> colombiers = colombierService.findAll();

        // Mapper les colombiers en DTO et ajouter les pigeons associés
        List<ColombierReponseDTO> colombierResponseDTOs = colombiers.stream()
                .map(colombier -> {
                    List<Pigeon> pigeons = pigeonService.findByColombierId(colombier.getId());
                    List<PigeonResponseDTO> pigeonDTOs = pigeons.stream()
                            .map(pigeon -> new PigeonResponseDTO(
                                    pigeon.id().toHexString(),
                                    pigeon.numeroBague(),
                                    pigeon.sexe(),
                                    pigeon.age(),
                                    pigeon.couleur()))
                            .collect(Collectors.toList());


                    ColombierReponseDTO dto = colombierMapper.toColombierResponseDTO(colombier);
                    dto.setPigeons(pigeonDTOs);
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(colombierResponseDTOs);
    }
}