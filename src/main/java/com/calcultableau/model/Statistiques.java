package com.calcultableau.model;

import java.time.LocalDate;

public class Statistiques {
    private String date;
    private Double moyenne;
    private Double mediane;

    // Constructeur par défaut
    public Statistiques() {
    }

    // Constructeur avec tous les champs
    public Statistiques(String date, Double moyenne, Double mediane) {
        this.date = date;
        this.moyenne = moyenne;
        this.mediane = mediane;
    }

    // Getters et setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(Double moyenne) {
        this.moyenne = moyenne;
    }

    public Double getMediane() {
        return mediane;
    }

    public void setMediane(Double mediane) {
        this.mediane = mediane;
    }
}