package com.pigeonskyrace.service;

import com.pigeonskyrace.model.Pigeon;
import com.pigeonskyrace.model.enums.Sexe;
import com.pigeonskyrace.repository.PigeonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
public class PigeonService {
    @Autowired
    private PigeonRepository pigeonRepository;

    public Pigeon save(Pigeon pigeon) {
        if (pigeon.getNumeroBague() == null) {
            pigeon.setNumeroBague(generateNumeroBague(pigeon.getSexe(), pigeon.getDateNaissance()));
        }
        return pigeonRepository.save(pigeon);
    }

    public List<Pigeon> findAll() {
        return pigeonRepository.findAll();
    }

    private String generateNumeroBague(Sexe sexe, LocalDate dateNaissance) {
        String prefix = (sexe == Sexe.FEMALE) ? "F" : "M";
        Random rand = new Random();
        int n = rand.nextInt(900) + 100; // Génère un nombre aléatoire de 3 chiffres
        String yearSuffix = dateNaissance != null
                ? dateNaissance.format(DateTimeFormatter.ofPattern("yy"))
                : LocalDate.now().format(DateTimeFormatter.ofPattern("yy")); // Utilise l'année courante si `dateNaissance` est null
        return prefix + "***" + n + "-" + yearSuffix;
    }
}
