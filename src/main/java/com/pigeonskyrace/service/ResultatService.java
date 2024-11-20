package com.pigeonskyrace.service;

import com.pigeonskyrace.dto.reponse.CompetionReponseDTO;
import com.pigeonskyrace.dto.reponse.ResultatReponseDTO;
import com.pigeonskyrace.dto.reponse.SaisonPigeonResponseDTO;
import com.pigeonskyrace.dto.request.ResultatRequestDTO;
import com.pigeonskyrace.exception.EntityNotFoundException;
import com.pigeonskyrace.mapper.CompetionMapper;
import com.pigeonskyrace.mapper.ResultatMapper;
import com.pigeonskyrace.model.*;
import com.pigeonskyrace.repository.ResultatRepository;
import com.pigeonskyrace.utils.Coordinates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        // La méthode retourne un DTO, donc pas de vérification de null ici
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

    /**
     * Calcule le classement et les points pour tous les résultats d'une compétition.
     */
    public List<ResultatReponseDTO> calculatePoint(CompetionReponseDTO competitionDto) {
        List<PigeonSaisonCompetition> competitionPigeon = pigeonSaisonCompetitionService.findByCompetition(competionMapper.toEntityy(competitionDto));
        log.info("Compétitions récupérées : {}", competitionPigeon);

        List<Resultat> results = new ArrayList<>();
        for (PigeonSaisonCompetition cp : competitionPigeon) {
            Optional<Resultat> optionalResult = resultatRepository.findByPigeonSaisonCompetition_Competition_Id(cp);
            if (optionalResult.isEmpty()) {
                log.warn("Aucun résultat trouvé pour PigeonSaisonCompetition : {}", cp);
            } else {
                results.add(optionalResult.get());
            }
        }

        if (results.isEmpty()) {
            throw new EntityNotFoundException("Aucun résultat trouvé pour cette compétition.");
        }

        List<Resultat> sortedResults = results.stream()
                .sorted((curr, next) -> Double.compare(next.getVitesse(), curr.getVitesse()))
                .toList();

        log.info("Résultats triés : {}", sortedResults);

        if (sortedResults.isEmpty()) {
            throw new EntityNotFoundException("Aucun résultat trié trouvé pour cette compétition.");
        }

        // Attribuer des points au premier
        sortedResults.get(0).setPoints(100.0);

        // Calcul de la distance moyenne
        double distanceMoyenne = sortedResults.stream()
                .mapToDouble(Resultat::getDistance)
                .average()
                .orElse(0.0);

        // Ajustement des vitesses
        sortedResults.forEach(resultat -> {
            double coefficient = distanceMoyenne / resultat.getDistance();
            resultat.setVitesse(resultat.getVitesse() * coefficient);
        });

        // Trier par vitesse décroissante
        sortedResults.sort((r1, r2) -> Double.compare(r2.getVitesse(), r1.getVitesse()));

        for (int i = 0; i < sortedResults.size(); i++) {
            Resultat resultat = sortedResults.get(i);
            resultat.setClassement(i + 1);
            resultat.setPoints(100.0 - (i * 100.0 / sortedResults.size()));
            resultatRepository.save(resultat);
        }

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
