package com.pigeonskyrace.service;

import com.pigeonskyrace.model.Colombier;
import com.pigeonskyrace.repository.ColombierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColombierService {
    @Autowired
    private ColombierRepository colombierRepository;

    public Optional<Colombier> findByNomColombier(String nomColombier) {
        return colombierRepository.findByNomColombier(nomColombier);
    }

    public Colombier save(Colombier colombier) {
        return colombierRepository.save(colombier);
    }

    public List<Colombier> findAll() {
        return colombierRepository.findAll();
    }

}
