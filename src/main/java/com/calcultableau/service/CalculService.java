package com.calcultableau.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CalculService {

    @Autowired
    private DisplayService displayService;

    /**
     * Méthode pour interface console (ancienne version)
     * Utilise Scanner et le DisplayService pour interagir avec l'utilisateur via la console.
     */
    public void executerCalcul() {
        int tab[] = new int[50];
        int i;
        int n = 0;
        int sum = 0;
        Scanner sc = new Scanner(System.in);

        // Demander la taille du tableau
        do {
            displayService.afficherMessage("Veuillez entrer la taille du tableau (max 50) :");
            n = sc.nextInt();
        } while (n > 50);

        // Remplissage du tableau
        displayService.afficherMessage("**** DEBUT PROGRAMME ****");
        for (i = 0; i < n; i++) {
            displayService.afficherMessage("Veuillez entrer un nombre :");
            tab[i] = sc.nextInt();
        }

        // Affichage des éléments
        displayService.afficherMessage("Les éléments du tableau sont :");
        for (i = 0; i < n; i++) {
            displayService.afficherMessage(String.valueOf(tab[i]));
        }

        // Calcul de la somme
        for (i = 0; i < n; i++) {
            sum += tab[i];
        }

        displayService.afficherMessage("La somme des éléments est : " + sum);
        displayService.afficherMessage("**** FIN PROGRAMME ****");

        sc.close();
    }

    /**
     * Méthode pour interface graphique JavaFX
     * Reçoit un tableau d'entiers et retourne la somme.
     * N'affiche rien, ne demande rien à l'utilisateur.
     *
     * @param tableau le tableau d'entiers à additionner
     * @return la somme des éléments
     */
    public int calculerSomme(int[] tableau) {
        int somme = 0;
        for (int valeur : tableau) {
            somme += valeur;
        }
        return somme;
    }
}
