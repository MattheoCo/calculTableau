package com.calcultableau.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Scanner;

@Service
public class CalculService {

    @Autowired
    private DisplayService displayService;

    public void executerCalcul() {
        int tab[] = new int[50];
        int i;
        int n = 0;
        int Sum = 0;
        Scanner sc = new Scanner(System.in);

        // LA TAILLE DU TABLEAU
        do {
            displayService.afficherMessage("Veuillez entrer la taille du tableau");
            n = sc.nextInt();
        } while (n > 50);

        // REMPLISSAGE DE TABLEAU
        displayService.afficherMessage("****DEBUT PROGRAMME****");
        for (i = 0; i < n; i++) {
            displayService.afficherMessage("Veuillez entrer un nombre");
            tab[i] = sc.nextInt();
        }

        // AFFICHAGE DE TABLEAU
        displayService.afficherMessage("Les élements de tableau sont : ");
        for (i = 0; i < n; i++) {
            displayService.afficherMessage(String.valueOf(tab[i]));
        }

        // AFFICHAGE DE SOMME
        for (i = 0; i < n; i++) {
            Sum += tab[i];
        }

        displayService.afficherMessage("La somme des éléments est égale à " + Sum);
        displayService.afficherMessage("****FIN PROGRAMME****");

        sc.close();
    }
}