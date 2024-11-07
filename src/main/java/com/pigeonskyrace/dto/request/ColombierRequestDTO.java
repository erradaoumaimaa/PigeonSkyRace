
    package com.pigeonskyrace.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

    @Data
    public class ColombierRequestDTO {

        @NotBlank(message = "Le nom du colombier est requis")
        @Size(max = 50, message = "Le nom du colombier ne doit pas dépasser 50 caractères")
        private String nomColombier;

        @Size(max = 100, message = "Les coordonnées GPS ne doivent pas dépasser 100 caractères")
        private String coordonneeGPS;
}
