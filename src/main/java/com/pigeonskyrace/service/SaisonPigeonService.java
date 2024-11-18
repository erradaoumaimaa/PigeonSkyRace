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

    public SaisonPigeonResponseDTO getSaisonPigeonBySaisonIdAndPigeonId(String saisonId, String pigeonId) {
        ObjectId saisonObjectId = new ObjectId(saisonId);
        ObjectId pigeonObjectId = new ObjectId(pigeonId);

        System.out.println("Recherche SaisonPigeon avec SaisonId: " + saisonObjectId + " et PigeonId: " + pigeonObjectId);

        SaisonPigeon saisonPigeon = saisonPigeonRepository.findBySaisonIdAndPigeonId(saisonObjectId, pigeonObjectId)
                .orElseThrow(() -> {
                    String errorMessage = "SaisonPigeon not found for SaisonId: " + saisonId + " and PigeonId: " + pigeonId;
                    System.out.println(errorMessage);
                    return new EntityNotFoundException(errorMessage);
                });

        return saisonPigeonMapper.toDto(saisonPigeon);
    }






}
