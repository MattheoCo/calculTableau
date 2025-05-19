package com.calcultableau.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Service de calcul pour les notes des étudiants
 */
@Service
public class CalculTab {

    private List<Integer> notes;

    public CalculTab() {
        notes = new ArrayList<>();
    }

    /**
     * Ajoute une note à la liste
     * @param note La note à ajouter
     */
    public void ajouterNote(int note) {
        notes.add(note);
    }

    /**
     * Calcule la moyenne des notes
     * @return La moyenne des notes
     */
    public double calculerMoyenne() {
        if (notes.isEmpty()) {
            return 0;
        }

        int somme = 0;
        for (int note : notes) {
            somme += note;
        }

        return (double) somme / notes.size();
    }

    /**
     * Trie les notes et calcule la médiane
     * @return La valeur médiane des notes
     */
    public double calculerMediane() {
        if (notes.isEmpty()) {
            return 0;
        }

        // Créer une copie triée de la liste
        List<Integer> noteTriees = new ArrayList<>(notes);
        Collections.sort(noteTriees);

        int taille = noteTriees.size();
        if (taille % 2 == 0) {
            // Si nombre pair d'éléments, moyenne des deux éléments du milieu
            return (noteTriees.get(taille/2 - 1) + noteTriees.get(taille/2)) / 2.0;
        } else {
            // Si nombre impair, élément du milieu
            return noteTriees.get(taille/2);
        }
    }

    /**
     * Renvoie le nombre de notes enregistrées
     * @return Le nombre de notes
     */
    public int getNombreNotes() {
        return notes.size();
    }

    /**
     * Réinitialise la liste des notes
     */
    public void viderNotes() {
        notes.clear();
    }

    /**
     * Retourne la liste des notes
     * @return La liste des notes
     */
    public List<Integer> getNotes() {
        return new ArrayList<>(notes); // Retourne une copie défensive
    }
}