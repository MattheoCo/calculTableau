package com.calcultableau.service;

import com.calcultableau.model.*;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClasseServiceTest {

    @Mock
    private JsonPersistenceService persistenceService;

    @Mock
    private CalculTab calculTab;

    private ClasseService classeService;

    @BeforeEach
    void setUp() {
        classeService = new ClasseService(persistenceService, calculTab);
    }

    @Test
    void testGetAllClasses() throws IOException {
        // Préparation
        Classe classe1 = new Classe(1L, "Classe A", new ArrayList<>());
        Classe classe2 = new Classe(2L, "Classe B", new ArrayList<>());

        Map<String, List<Classe>> data = new HashMap<>();
        data.put("classes", List.of(classe1, classe2));

        when(persistenceService.chargerDonnees(eq("data/classes.json"), any(TypeReference.class)))
                .thenReturn(data);

        // Test
        List<Classe> result = classeService.getAllClasses();

        // Vérification
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());
    }

    @Test
    void testMettreAJourNotes() throws IOException {
        // Préparation
        Statistiques stats = new Statistiques("2023-05-19", 15.0, 16.0);
        Note note1 = new Note(1L, List.of(15, 16), 15.5);
        Note note2 = new Note(2L, List.of(17, 18), 17.5);

        Matiere matiere = new Matiere(1L, "Math", "MAT101", 1L, new ArrayList<>(List.of(note1)), stats);
        Classe classe = new Classe(1L, "Classe A", new ArrayList<>(List.of(matiere)));

        Map<String, List<Classe>> data = new HashMap<>();
        data.put("classes", List.of(classe));

        when(persistenceService.chargerDonnees(eq("data/classes.json"), any(TypeReference.class)))
                .thenReturn(data);
        when(calculTab.calculerMoyenne()).thenReturn(16.0);
        when(calculTab.calculerMediane()).thenReturn(17.0);

        // Nouvelle note à ajouter/mettre à jour
        Note nouvelleNote = new Note(2L, List.of(17, 18), 17.5);

        // Test
        classeService.mettreAJourNotes(1L, 1L, nouvelleNote);

        // Vérifications
        verify(persistenceService).sauvegarderDonnees(eq("data/classes.json"), any(Map.class));
        verify(calculTab).viderNotes();
        verify(calculTab, times(4)).ajouterNote(anyInt()); // 2 notes dans note1 + 2 notes dans nouvelleNote
    }
}