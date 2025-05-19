package com.calcultableau.model;

import java.util.List;

public class Matiere {
    private Long id;
    private String nom;
    private String code;
    private Long enseignantId;
    private List<Note> notes;
    private Statistiques statistiques;

    // Constructeur par défaut
    public Matiere() {
    }

    // Constructeur avec tous les champs
    public Matiere(Long id, String nom, String code, Long enseignantId, List<Note> notes, Statistiques statistiques) {
        this.id = id;
        this.nom = nom;
        this.code = code;
        this.enseignantId = enseignantId;
        this.notes = notes;
        this.statistiques = statistiques;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getEnseignantId() {
        return enseignantId;
    }

    public void setEnseignantId(Long enseignantId) {
        this.enseignantId = enseignantId;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public Statistiques getStatistiques() {
        return statistiques;
    }

    public void setStatistiques(Statistiques statistiques) {
        this.statistiques = statistiques;
    }
}