package com.pigeonskyrace.service;

import com.pigeonskyrace.dto.reponse.CompetionReponseDTO;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfGenerationService {

    public byte[] generateCompetitionResultPdf(CompetionReponseDTO competionResult) throws IOException {
        // Crée un flux de sortie pour le PDF
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Crée un document PDF
        Document document = new Document();
        try {
            // Ajoute un écrivain PDF
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // Ajoute les informations sur la compétition
            document.add(new Paragraph("Résultats de la compétition"));
            document.add(new Paragraph("Nom de la compétition : " + competionResult.getNom()));
            document.add(new Paragraph("Date de la compétition : " + competionResult.getStartTime()));



        } catch (Exception e) {
            throw new IOException("Erreur lors de la génération du PDF", e);
        } finally {
            // Fermer le document pour finaliser le PDF
            document.close();
        }

        // Retourne le fichier PDF sous forme de tableau de bytes
        return outputStream.toByteArray();
    }
}
