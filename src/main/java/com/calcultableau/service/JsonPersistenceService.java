package com.calcultableau.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JsonPersistenceService {

    private final ObjectMapper objectMapper;

    public JsonPersistenceService() {
        this.objectMapper = new ObjectMapper();
    }

    public <T> T chargerDonnees(String chemin, Class<T> classe) throws IOException {
        try (InputStream is = getResource(chemin).getInputStream()) {
            return objectMapper.readValue(is, classe);
        }
    }

    public <T> T chargerDonnees(String chemin, TypeReference<T> typeReference) throws IOException {
        try (InputStream is = getResource(chemin).getInputStream()) {
            return objectMapper.readValue(is, typeReference);
        }
    }

    public <T> void sauvegarderDonnees(String chemin, T donnees) throws IOException {
        File file = getResource(chemin).getFile();
        objectMapper.writeValue(file, donnees);
    }

    public ClassPathResource getResource(String chemin) {
        return new ClassPathResource(chemin);
    }
}