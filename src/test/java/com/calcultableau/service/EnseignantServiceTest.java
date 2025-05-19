package com.calcultableau.service;

import com.calcultableau.model.Enseignant;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnseignantServiceTest {

    @Mock
    private JsonPersistenceService persistenceService;

    private EnseignantService enseignantService;

    @BeforeEach
    void setUp() {
        enseignantService = new EnseignantService(persistenceService);
    }

    @Test
    void testGetAllEnseignants() throws IOException {
        // Préparation
        Enseignant enseignant1 = new Enseignant(1L, "Jean", "Dupont", "jean@test.com", "password");
        Enseignant enseignant2 = new Enseignant(2L, "Marie", "Martin", "marie@test.com", "password");

        Map<String, List<Enseignant>> data = new HashMap<>();
        data.put("enseignants", List.of(enseignant1, enseignant2));

        when(persistenceService.chargerDonnees(eq("data/enseignants.json"), any(TypeReference.class)))
                .thenReturn(data);

        // Test
        List<Enseignant> result = enseignantService.getAllEnseignants();

        // Vérification
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());
    }

    @Test
    void testAuthentifier_Success() throws IOException {
        // Préparation
        Enseignant enseignant = new Enseignant(1L, "Jean", "Dupont", "jean@test.com", "password");

        Map<String, List<Enseignant>> data = new HashMap<>();
        data.put("enseignants", List.of(enseignant));

        when(persistenceService.chargerDonnees(eq("data/enseignants.json"), any(TypeReference.class)))
                .thenReturn(data);

        // Test
        Optional<Enseignant> result = enseignantService.authentifier("jean@test.com", "password");

        // Vérification
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testAuthentifier_Failure() throws IOException {
        // Préparation
        Enseignant enseignant = new Enseignant(1L, "Jean", "Dupont", "jean@test.com", "password");

        Map<String, List<Enseignant>> data = new HashMap<>();
        data.put("enseignants", List.of(enseignant));

        when(persistenceService.chargerDonnees(eq("data/enseignants.json"), any(TypeReference.class)))
                .thenReturn(data);

        // Test
        Optional<Enseignant> result = enseignantService.authentifier("jean@test.com", "wrongpassword");

        // Vérification
        assertFalse(result.isPresent());
    }
}