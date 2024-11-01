package com.pigeonskyrace.dto.request;

import com.pigeonskyrace.model.enums.Sexe;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PigeonRequestDTO {
    @NotBlank(message = "Le numéro de bague est requis")
    @Size(max = 50)
    private String numeroBague;

    @NotNull(message = "Le sexe est requis")
    private Sexe sexe;

    @NotNull(message = "L'âge est requis")
    private Integer age;

    @NotBlank(message = "La couleur est requise")
    @Size(max = 50)
    private String couleur;

    private String colombier;
}
