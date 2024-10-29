package com.pigeonskyrace.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pigeons")
public class Pigeon {
    @Id
    private String id;

    @NotBlank(message = "Le numéro de bague est obligatoire")
    @Size(min = 3, max = 10, message = "Le numéro de bague doit avoir entre 3 et 10 caractères")
    private String numeroBague;

    @NotBlank(message = "Le sexe doit être spécifié")
    private String sexe;

    @NotNull(message = "L'âge est obligatoire")
    @Positive(message = "L'âge doit être un nombre positif")
    private Integer age;

    private String couleur;
}
