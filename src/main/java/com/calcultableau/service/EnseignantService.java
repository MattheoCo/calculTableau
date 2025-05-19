package com.calcultableau.service;

import com.calcultableau.model.Enseignant;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EnseignantService {

    private final JsonPersistenceService persistenceService;
    private static final String ENSEIGNANTS_FILE = "data/enseignants.json";

    @Autowired
    public EnseignantService(JsonPersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    public List<Enseignant> getAllEnseignants() throws IOException {
        Map<String, List<Enseignant>> data = persistenceService.chargerDonnees(
                ENSEIGNANTS_FILE,
                new TypeReference<HashMap<String, List<Enseignant>>>() {}
        );
        return data.get("enseignants");
    }

    public Optional<Enseignant> authentifier(String email, String password) throws IOException {
        return getAllEnseignants().stream()
                .filter(e -> e.getEmail().equals(email) && e.getPassword().equals(password))
                .findFirst();
    }

    public Optional<Enseignant> getEnseignantById(Long id) throws IOException {
        return getAllEnseignants().stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }
}