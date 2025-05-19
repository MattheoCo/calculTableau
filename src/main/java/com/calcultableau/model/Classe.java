package com.calcultableau.model;

import java.util.List;

public class Classe {
    private Long id;
    private String nom;
    private List<Matiere> matieres;

    // Constructeur par défaut
    public Classe() {
    }

    // Constructeur avec tous les champs
    public Classe(Long id, String nom, List<Matiere> matieres) {
        this.id = id;
        this.nom = nom;
        this.matieres = matieres;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Matiere> getMatieres() {
        return matieres;
    }

    public void setMatieres(List<Matiere> matieres) {
        this.matieres = matieres;
    }
}