package com.calcultableau.model;

import java.util.Objects;

public class Enseignant {
    private Long id;
    private String prenom;
    private String nom;
    private String email;
    private String password;

    // Constructeur par défaut nécessaire pour la désérialisation JSON
    public Enseignant() {
    }

    // Constructeur avec tous les champs
    public Enseignant(Long id, String prenom, String nom, String email, String password) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.password = password;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Méthode toString pour afficher les informations de l'enseignant
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enseignant that = (Enseignant) o;
        return Objects.equals(id, that.id);
    }

    // Méthode hashCode pour garantir l'unicité de l'enseignant dans les collections
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}