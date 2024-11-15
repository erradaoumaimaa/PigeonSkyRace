package com.pigeonskyrace.service;

import com.pigeonskyrace.exception.EntityNotFoundException;
import com.pigeonskyrace.model.Colombier;
import com.pigeonskyrace.repository.ColombierRepository;
import com.pigeonskyrace.utils.Coordinates;
import org.bson.types.ObjectId;
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
    /**
     * Récupère les coordonnées GPS du colombier associé à un ID.
     *
     * @param id L'identifiant du colombier.
     * @return Les coordonnées GPS sous forme d'objet `Coordinates`.
     */

    public Coordinates getLoftCoordinates(ObjectId id) {
        Colombier colombier = colombierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Colombier avec l'ID " + id + " introuvable"));

        return new Coordinates(colombier.getCoordonneeGPSlatitude(), colombier.getCoordonneeGPSlongitude());
    }

}
