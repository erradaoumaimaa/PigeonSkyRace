package com.pigeonskyrace.service;

import com.pigeonskyrace.model.Saison;
import com.pigeonskyrace.repository.SaisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaisonService {
    @Autowired
    private SaisonRepository saisonRepository;

    public Saison save(Saison saison) {
        return saisonRepository.save(saison);
    }

    public List<Saison> findAll() {
        return saisonRepository.findAll();
    }
}
