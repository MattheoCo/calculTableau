package com.calcultableau.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculTabTest {

    private CalculTab calculTab;

    @BeforeEach
    void setUp() {
        calculTab = new CalculTab();
    }

    @Test
    void testCalculerMoyenne() {
        // Préparation
        calculTab.ajouterNote(10);
        calculTab.ajouterNote(15);
        calculTab.ajouterNote(20);

        // Test
        double moyenne = calculTab.calculerMoyenne();

        // Vérification
        assertEquals(15.0, moyenne);
    }

    @Test
    void testCalculerMediane_NombrePair() {
        // Préparation
        calculTab.ajouterNote(10);
        calculTab.ajouterNote(15);
        calculTab.ajouterNote(20);
        calculTab.ajouterNote(25);

        // Test
        double mediane = calculTab.calculerMediane();

        // Vérification
        assertEquals(17.5, mediane);
    }

    @Test
    void testCalculerMediane_NombreImpair() {
        // Préparation
        calculTab.ajouterNote(10);
        calculTab.ajouterNote(15);
        calculTab.ajouterNote(20);

        // Test
        double mediane = calculTab.calculerMediane();

        // Vérification
        assertEquals(15.0, mediane);
    }

    @Test
    void testViderNotes() {
        // Préparation
        calculTab.ajouterNote(10);
        calculTab.ajouterNote(15);

        // Test
        calculTab.viderNotes();

        // Vérification
        assertEquals(0.0, calculTab.calculerMoyenne());
    }
}