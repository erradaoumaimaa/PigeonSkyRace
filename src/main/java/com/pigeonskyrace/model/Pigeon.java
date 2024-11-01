package com.pigeonskyrace.model;

import com.pigeonskyrace.model.enums.Sexe;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pigeons")
public class Pigeon {

    @MongoId
    private ObjectId id;

    @Indexed(unique = true)
    @NotBlank(message = "Le numéro de bague est requis")
    @Size(max = 50)
    private String numeroBague;

    @Indexed
    @NotBlank(message = "Le sexe est requis")
    private Sexe sexe;

    @NotBlank(message = "L'âge est requis")
    private Integer age;

    @NotBlank(message = "La couleur est requise")
    @Size(max = 50)
    private String couleur;


    // Référence au colombier auquel ce pigeon appartient
    @DBRef
    private Colombier colombier;
}
