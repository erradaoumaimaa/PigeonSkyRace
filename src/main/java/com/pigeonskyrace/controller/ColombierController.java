package com.pigeonskyrace.controller;
import com.pigeonskyrace.dto.reponse.ColombierReponseDTO;
import com.pigeonskyrace.dto.reponse.UserResponseDTO;
import com.pigeonskyrace.mapper.ColombierMapper;
import com.pigeonskyrace.model.Colombier;
import com.pigeonskyrace.service.ColombierService;
import jakarta.servlet.http.HttpSession;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/colombiers")
public class ColombierController {

    @Autowired
    private ColombierService colombierService;

    @Autowired
    private ColombierMapper colombierMapper;

    @PostMapping
    public ResponseEntity<ColombierReponseDTO> createColombier(
            @RequestBody UserResponseDTO.ColombierRequestDTO colombierRequestDTO,
            HttpSession session) {

        ObjectId userId = (ObjectId) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        // Utilisez le mapper pour créer le Colombier avec l'ID du propriétaire
        Colombier colombier = colombierMapper.toColombier(colombierRequestDTO, userId);

        Colombier savedColombier = colombierService.save(colombier);
        return ResponseEntity.ok(colombierMapper.toColombierResponseDTO(savedColombier));
    }


    @GetMapping
    public ResponseEntity<List<ColombierReponseDTO>> getAllColombiers() {
        List<Colombier> colombiers = colombierService.findAll();
        List<ColombierReponseDTO> colombierResponseDTOs = colombiers.stream()
                .map(colombierMapper::toColombierResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(colombierResponseDTOs);

    }
}