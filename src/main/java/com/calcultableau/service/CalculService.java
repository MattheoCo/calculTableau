package com.calcultableau.service;

import com.calcultableau.model.UtilisateurTab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Scanner;

@Service
public class CalculService {

    @Autowired
    private DisplayService displayService;

    @Autowired
    private CalculTab calculTab;

    public void executerCalcul() {
        Scanner sc = new Scanner(System.in);

        // Création de l'utilisateur (enseignant)
        UtilisateurTab enseignant = null;
        try {
            enseignant = new UtilisateurTab("Alain", "Dupont", "alain.dupont@iut.fr", LocalDate.now());
            displayService.afficherMessage("Enseignant créé: " + enseignant.getPrenom() + " " + enseignant.getNom());
        } catch (IllegalArgumentException e) {
            displayService.afficherMessage("Erreur: " + e.getMessage());
            sc.close();
            return;
        }

        // Saisie des notes
        displayService.afficherMessage("****DEBUT PROGRAMME****");

        int nombreEtudiants = 0;
        do {
            displayService.afficherMessage("Veuillez entrer le nombre d'étudiants (max 50)");
            nombreEtudiants = sc.nextInt();
        } while (nombreEtudiants <= 0 || nombreEtudiants > 50);

        calculTab.viderNotes();

        // Saisie des notes des étudiants
        for (int i = 0; i < nombreEtudiants; i++) {
            displayService.afficherMessage("Veuillez entrer la note de l'étudiant " + (i+1));
            int note = sc.nextInt();
            calculTab.ajouterNote(note);
        }

        // Calculs
        double moyenne = calculTab.calculerMoyenne();
        double mediane = calculTab.calculerMediane();

        // Affichage des résultats
        displayService.afficherMessage("Nombre d'étudiants notés: " + calculTab.getNombreNotes());
        displayService.afficherMessage("Moyenne du groupe: " + String.format("%.2f", moyenne));
        displayService.afficherMessage("Médiane du groupe: " + String.format("%.2f", mediane));

        // Mise à jour et stockage des résultats
        enseignant.setResultats(nombreEtudiants, moyenne, mediane);
        displayService.afficherMessage("Résultat stocké: " + enseignant);

        displayService.afficherMessage("****FIN PROGRAMME****");

        sc.close();
    }
}