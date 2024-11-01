package com.pigeonskyrace.service;

import com.pigeonskyrace.model.Pigeon;
import com.pigeonskyrace.repository.PigeonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PigeonService {
    @Autowired
    private PigeonRepository pigeonRepository;

    public Pigeon save(Pigeon pigeon) {
        return pigeonRepository.save(pigeon);
    }

    public List<Pigeon> findAll() {
        return pigeonRepository.findAll();
    }
}
