package com.pigeonskyrace.controller;

import com.pigeonskyrace.dto.reponse.CompetionReponseDTO;
import com.pigeonskyrace.service.CompetionService;
import com.pigeonskyrace.service.PdfGenerationService;
import com.pigeonskyrace.utils.CompetitionId;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PdfController {

    @Autowired
    private CompetionService competionService;

    @Autowired
    private PdfGenerationService pdfGenerationService;

    @GetMapping("/api/v1/resultats/{competitionId}/pdf")
    public ResponseEntity<byte[]> generateCompetitionPdf(@PathVariable String competitionId) throws DocumentException, IOException {
        // Récupérer les informations de la compétition à partir de la base de données
        CompetionReponseDTO competionResult = competionService.getCompetitionById(competitionId);

        // Générer le PDF avec les informations de la compétition
        byte[] pdfBytes = pdfGenerationService.generateCompetitionResultPdf(competionResult);

        // Définir les en-têtes pour télécharger le fichier PDF
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=competition_results.pdf");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);

        // Retourner la réponse avec le PDF généré
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}
