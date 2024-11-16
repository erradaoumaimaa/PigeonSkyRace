package com.pigeonskyrace.service;

import com.pigeonskyrace.dto.reponse.PigeonResponseDTO;
import com.pigeonskyrace.dto.request.PigeonRequestDTO;
import com.pigeonskyrace.exception.EntityNotFoundException;
import com.pigeonskyrace.mapper.PigeonMapper;
import com.pigeonskyrace.model.Colombier;
import com.pigeonskyrace.model.Pigeon;
import com.pigeonskyrace.model.enums.Sexe;
import com.pigeonskyrace.repository.PigeonRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PigeonService {
    @Autowired
    private  PigeonRepository pigeonRepository;

    @Autowired
    private  ColombierService colombierService;

    @Autowired
    private  PigeonMapper pigeonMapper;

    public PigeonResponseDTO createPigeon(PigeonRequestDTO pigeonRequestDTO) {


        Colombier colombier = colombierService.findById(new ObjectId(pigeonRequestDTO.getColombierId()))
                .orElseThrow(() -> new RuntimeException("Colombier non trouvé pour l'ID : " + pigeonRequestDTO.getColombierId()));


        Pigeon pigeon = pigeonMapper.toPigeon(pigeonRequestDTO);

        // Créer un nouveau Pigeon avec le colombier associé
        pigeon = new Pigeon(
                pigeon.id(),
                pigeon.numeroBague(),
                pigeon.sexe(),
                pigeon.age(),
                pigeon.couleur(),
                colombier
        );


        Pigeon savedPigeon = pigeonRepository.save(pigeon);

        return pigeonMapper.toPigeonResponseDTO(savedPigeon);
    }

    public List<Pigeon> findAll() {
        return pigeonRepository.findAll();
    }


    public Pigeon findByNumeroBague(String numeroBague) {
        return pigeonRepository.findByNumeroBague(numeroBague)
                .orElseThrow(() -> new EntityNotFoundException("Pigeon avec numéro de bague " + numeroBague + " introuvable"));
    }

    // Génère un numéro de bague basé sur le sexe et l'âge
    private String generateNumeroBague(Sexe sexe, Integer age) {
        String prefix = (sexe == Sexe.FEMALE) ? "F" : "M";
        Random rand = new Random();
        int n = rand.nextInt(90) + 10; // Génère un nombre à deux chiffres
        // Calcul de l'année de naissance
        int birthYear = LocalDate.now().minusYears(age).getYear();
        String yearSuffix = String.valueOf(birthYear).substring(2); // Prend les deux derniers chiffres de l'année
        return prefix + "**" + n + "-" + yearSuffix;
    }
}