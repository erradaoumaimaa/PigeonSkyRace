package com.pigeonskyrace.service;

import com.pigeonskyrace.dto.reponse.CompetionReponseDTO;
import com.pigeonskyrace.dto.reponse.PigeonSaisonCompetitionResponseDTO;
import com.pigeonskyrace.dto.reponse.ResultatReponseDTO;
import com.pigeonskyrace.dto.reponse.SaisonPigeonResponseDTO;
import com.pigeonskyrace.dto.request.PigeonSaisonCompetitionRequestDTO;
import com.pigeonskyrace.dto.request.ResultatRequestDTO;
import com.pigeonskyrace.exception.EntityNotFoundException;
import com.pigeonskyrace.mapper.CompetionMapper;
import com.pigeonskyrace.mapper.PigeonSaisonCompetitionMapper;
import com.pigeonskyrace.mapper.ResultatMapper;
import com.pigeonskyrace.model.*;
import com.pigeonskyrace.repository.ResultatRepository;
import com.pigeonskyrace.utils.Coordinates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class ResultatService {

    private final PigeonService pigeonService;
    private final SaisonPigeonService saisonPigeonService;
    private final PigeonSaisonCompetitionService pigeonSaisonCompetitionService;
    private final ColombierService colombierService;
    private final ResultatRepository resultatRepository;
    private final PigeonSaisonCompetitionMapper pigeonSaisonCompetitionMapper;
    private final CompetionMapper competionMapper;
    private final ResultatMapper mapper;

    /**
     * Crée un résultat pour un pigeon dans une compétition et calcule sa vitesse.
     */
    public ResultatReponseDTO createResult(ResultatRequestDTO resultatRequestDTO, CompetionReponseDTO competition) throws ChangeSetPersister.NotFoundException {
        // Vérifier l'existence du pigeon
        Pigeon pigeon = pigeonService.findByNumeroBague(resultatRequestDTO.numeroBague());
        if (pigeon == null) {
            throw new EntityNotFoundException("Pigeon introuvable pour le numéro de bague : " + resultatRequestDTO.numeroBague());
        }

        // Récupérer SaisonPigeon
        SaisonPigeonResponseDTO saisonPigeonResponseDTO = saisonPigeonService.getSaisonPigeonBySaisonIdAndPigeonId(
                competition.getSaisonId(), pigeon.getId().toHexString());

        // Récupérer PigeonSaisonCompetition


        PigeonSaisonCompetition pigeonSaisonCompetition = pigeonSaisonCompetitionService
                .findBySeasonPigeonAndCompetition(saisonPigeonResponseDTO.toEntity(), competionMapper.toEntityy(competition))
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune participation trouvée pour le pigeon " + pigeon.getId() +
                                " dans la compétition " + competition.getId()));


        Resultat resultat = mapper.toEntity(resultatRequestDTO);
        resultat.setPigeonSaisonCompetition(pigeonSaisonCompetition);

        // Calculer la distance
        Coordinates coordonneesColombier = colombierService.getLoftCoordinates(pigeon.getColombier().getId());
        double distance = calculerDistance(
                coordonneesColombier.getLatitude(), coordonneesColombier.getLongitude(),
                competition.getLatitudeGPS(), competition.getLongitudeGPS());

        resultat.setDistance(distance);

        // Calculer le temps de vol
        Duration tempsDeVol = Duration.between(competition.getStartTime(), resultat.getDateArrivee());
        resultat.setVitesse(calculerVitesse(distance, tempsDeVol));

        // Sauvegarder le résultat
        resultatRepository.save(resultat);

        return mapper.toReponseDTO(resultat);
    }

    public List<ResultatReponseDTO> calculatePoint(CompetionReponseDTO competitionDto) {
        // Retrieve competition pigeons
        List<PigeonSaisonCompetition> competitionPigeon = pigeonSaisonCompetitionService
                .findByCompetition(competionMapper.toEntityy(competitionDto));

        log.info("Competition Entity: {}", competionMapper.toEntityy(competitionDto));
        log.info("Pigeons retrieved: {}", competitionPigeon);

        // Collect results
        List<Resultat> results = new ArrayList<>();
        for (PigeonSaisonCompetition cp : competitionPigeon) {
            Optional<Resultat> optionalResult = resultatRepository.findByPigeonSaisonCompetition(cp);
            optionalResult.ifPresentOrElse(results::add, () ->
                    log.warn("No result found for PigeonSaisonCompetition: {}", cp)
            );
        }

        if (results.isEmpty()) {
            throw new EntityNotFoundException("No results found for this competition.");
        }

        // Sort results by speed (descending)
        List<Resultat> sortedResults = results.stream()
                .sorted((curr, next) -> Double.compare(next.getVitesse(), curr.getVitesse()))
                .toList();
        log.info("Sorted Results: {}", sortedResults);

        // Compute average distance
        double distanceMoyenne = sortedResults.stream()
                .mapToDouble(Resultat::getDistance)
                .average()
                .orElse(0.0);

        log.info("Average Distance: {}", distanceMoyenne);

        // Adjust speed based on average distance
        sortedResults.forEach(resultat -> {
            if (resultat.getDistance() > 0) {
                double coefficient = distanceMoyenne / resultat.getDistance();
                resultat.setVitesse(resultat.getVitesse() * coefficient);
            } else {
                log.warn("Invalid distance for result: {}", resultat);
            }
        });

        // Re-sort by adjusted speed
        sortedResults.sort((r1, r2) -> Double.compare(r2.getVitesse(), r1.getVitesse()));

        // Assign points and rankings
        for (int i = 0; i < sortedResults.size(); i++) {
            Resultat resultat = sortedResults.get(i);
            resultat.setClassement(i + 1);
            resultat.setPoints(100.0 - (i * 100.0 / sortedResults.size()));
            resultatRepository.save(resultat);
        }

        // Convert results to DTO
        return sortedResults.stream()
                .map(mapper::toReponseDTO)
                .toList();
    }



    private double calculerVitesse(double distance, Duration tempsDeVol) {
        if (tempsDeVol.toMinutes() <= 0) {
            throw new IllegalArgumentException("Le temps de vol doit être supérieur à 0.");
        }
        return (distance * 1000) / tempsDeVol.toMinutes(); // Vitesse en m/min
    }


    private double calculerDistance(double lat1, double lon1, double lat2, double lon2) {
        // Haversine Formula
        double latRad1 = Math.toRadians(lat1);
        double lonRad1 = Math.toRadians(lon1);
        double latRad2 = Math.toRadians(lat2);
        double lonRad2 = Math.toRadians(lon2);

        double deltaLat = latRad2 - latRad1;
        double deltaLon = lonRad2 - lonRad1;

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(latRad1) * Math.cos(latRad2)
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        return 6371.01 * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)); // Distance en km
    }








}