package com.pigeonskyrace.service;

import com.pigeonskyrace.dto.reponse.SaisonPigeonResponseDTO;
import com.pigeonskyrace.dto.request.SaisonPigeonRequestDTO;
import com.pigeonskyrace.exception.EntityNotFoundException;
import com.pigeonskyrace.mapper.SaisonPigeonMapper;
import com.pigeonskyrace.model.SaisonPigeon;
import com.pigeonskyrace.repository.SaisonPigeonRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaisonPigeonService {

    @Autowired
    private SaisonPigeonRepository saisonPigeonRepository;

    @Autowired
    private SaisonPigeonMapper saisonPigeonMapper;

    public SaisonPigeonResponseDTO addPigeonToSaison(String saisonId, SaisonPigeonRequestDTO requestDTO) {
        SaisonPigeon saisonPigeon = saisonPigeonMapper.toEntity(requestDTO, saisonId);
        SaisonPigeon savedSaisonPigeon = saisonPigeonRepository.save(saisonPigeon);
        return saisonPigeonMapper.toDto(savedSaisonPigeon);
    }

    // Méthode pour obtenir une association spécifique par ID
    public SaisonPigeonResponseDTO getSaisonPigeonById(String id) {
        SaisonPigeon saisonPigeon = saisonPigeonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SaisonPigeon not found with ID: " + id));
        return saisonPigeonMapper.toDto(saisonPigeon);
    }

    public SaisonPigeon getSaisonPigeonBySaisonIdAndPigeonId(String saisonId, String pigeonId) {
        return saisonPigeonRepository.findBySaisonIdAndPigeonId(saisonId, pigeonId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "SaisonPigeon introuvable pour SaisonId " + saisonId + " et PigeonId " + pigeonId
                ));
    }






}
