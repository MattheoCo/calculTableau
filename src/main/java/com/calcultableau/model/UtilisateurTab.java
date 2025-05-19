package com.calcultableau.model;

import java.time.LocalDate;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class UtilisateurTab {
    private String prenom;
    private String nom;
    private String email;
    private LocalDate dateExamen;
    private int nombreEtudiants;
    private double moyenne;
    private double mediane;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("[\\w.-]+@[\\w.-]+\\.[a-z]{2,}");

    public UtilisateurTab(String prenom, String nom, String email, LocalDate dateExamen) {
        this.prenom = prenom;
        this.nom = nom;
        setEmail(email);
        this.dateExamen = dateExamen;
    }

    public void setEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Format d'email invalide");
        }
        this.email = email;
    }

    public void setResultats(int nombreEtudiants, double moyenne, double mediane) {
        this.nombreEtudiants = nombreEtudiants;
        this.moyenne = moyenne;
        this.mediane = mediane;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(prenom).append(", ");
        sb.append(nom).append(", ");
        sb.append(email).append(", ");
        sb.append(dateExamen).append(", ");
        sb.append(nombreEtudiants).append(", ");
        sb.append(String.format("%.2f", moyenne)).append(", ");
        sb.append(String.format("%.2f", mediane));
        return sb.toString();
    }

    // Getters et setters
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getEmail() { return email; }

    public LocalDate getDateExamen() { return dateExamen; }
    public void setDateExamen(LocalDate dateExamen) { this.dateExamen = dateExamen; }

    public int getNombreEtudiants() { return nombreEtudiants; }
    public double getMoyenne() { return moyenne; }
    public double getMediane() { return mediane; }
}