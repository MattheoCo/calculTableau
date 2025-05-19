package com.calcultableau.model;

import java.util.List;

public class Note {
    private Long etudiantId;
    private List<Integer> valeurs;
    private Double moyenne;

    // Constructeur par défaut
    public Note() {
    }

    // Constructeur avec tous les champs
    public Note(Long etudiantId, List<Integer> valeurs, Double moyenne) {
        this.etudiantId = etudiantId;
        this.valeurs = valeurs;
        this.moyenne = moyenne;
    }

    // Getters et setters
    public Long getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(Long etudiantId) {
        this.etudiantId = etudiantId;
    }

    public List<Integer> getValeurs() {
        return valeurs;
    }

    public void setValeurs(List<Integer> valeurs) {
        this.valeurs = valeurs;
    }

    public Double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(Double moyenne) {
        this.moyenne = moyenne;
    }
}