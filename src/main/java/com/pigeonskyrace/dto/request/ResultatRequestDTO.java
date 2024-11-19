package com.pigeonskyrace.dto.request;

import com.pigeonskyrace.model.PigeonSaisonCompetition;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record ResultatRequestDTO(

        @NotNull String numeroBague,

        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime dateArrivee

) {}
