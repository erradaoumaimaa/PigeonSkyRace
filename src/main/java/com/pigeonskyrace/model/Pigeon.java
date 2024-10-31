package com.pigeonskyrace.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "pigeons")
public class Pigeon {
    @Id
    private String id;
    private String numeroBague;
    private String sexe;
    private Integer age;
    private String couleur;

    // Constructeur sans id
    public Pigeon(String numeroBague, String sexe, Integer age, String couleur) {
        this.numeroBague = numeroBague;
        this.sexe = sexe;
        this.age = age;
        this.couleur = couleur;
    }

    // Constructeur avec id
    public Pigeon(String id, String numeroBague, String sexe, Integer age, String couleur) {
        this.id = id;
        this.numeroBague = numeroBague;
        this.sexe = sexe;
        this.age = age;
        this.couleur = couleur;
    }


    public String getNumeroBague() {
        return numeroBague;
    }

    public void setNumeroBague(String numeroBague) {
        this.numeroBague = numeroBague;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
}
