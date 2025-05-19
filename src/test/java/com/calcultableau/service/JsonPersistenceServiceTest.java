package com.calcultableau.service;

import com.calcultableau.model.Enseignant;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JsonPersistenceServiceTest {

    private JsonPersistenceService jsonPersistenceService;

    @Mock
    private ClassPathResource mockResource;

    @BeforeEach
    void setUp() {
        jsonPersistenceService = spy(new JsonPersistenceService());
        doReturn(mockResource).when(jsonPersistenceService).getResource(anyString());
    }

    @Test
    void testChargerDonnees_Class() throws Exception {
        // Préparation des données de test
        String jsonContent = "{\"nom\": \"Test\", \"id\": 1}";
        InputStream inputStream = new ByteArrayInputStream(jsonContent.getBytes());
        when(mockResource.getInputStream()).thenReturn(inputStream);

        // Test
        TestClass result = jsonPersistenceService.chargerDonnees("test.json", TestClass.class);

        // Vérification
        assertNotNull(result);
        assertEquals("Test", result.nom);
        assertEquals(1, result.id);
    }

    @Test
    void testChargerDonnees_TypeReference() throws Exception {
        // Préparation des données de test
        String jsonContent = "{\"enseignants\": [{\"id\": 1, \"nom\": \"Dubois\", \"prenom\": \"Jean\"}]}";
        InputStream inputStream = new ByteArrayInputStream(jsonContent.getBytes());
        when(mockResource.getInputStream()).thenReturn(inputStream);

        // Test
        Map<String, List<Map<String, Object>>> result = jsonPersistenceService.chargerDonnees(
                "test.json",
                new TypeReference<HashMap<String, List<Map<String, Object>>>>() {}
        );

        // Vérification
        assertNotNull(result);
        assertEquals(1, result.get("enseignants").size());
        assertEquals(1, result.get("enseignants").get(0).get("id"));
    }

    @Test
    void testSauvegarderDonnees() throws Exception {
        // Création d'un fichier temporaire réel pour le test
        File tempFile = File.createTempFile("test", ".json");
        tempFile.deleteOnExit(); // Supprime le fichier après le test

        // Configuration du mock pour retourner ce fichier réel
        when(mockResource.getFile()).thenReturn(tempFile);

        TestClass testObject = new TestClass();
        testObject.nom = "Test";
        testObject.id = 1;

        // Test
        jsonPersistenceService.sauvegarderDonnees("test.json", testObject);

        // Vérification que le fichier a été écrit (facultatif)
        assertTrue(tempFile.length() > 0);
    }

    // Classe pour les tests
    static class TestClass {
        public String nom;
        public int id;
    }
}