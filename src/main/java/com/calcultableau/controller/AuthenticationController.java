package com.calcultableau.controller;

import com.calcultableau.model.Enseignant;
import com.calcultableau.service.EnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final EnseignantService enseignantService;

    @Autowired
    public AuthenticationController(EnseignantService enseignantService) {
        this.enseignantService = enseignantService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        try {
            String email = credentials.get("email");
            String password = credentials.get("password");

            Optional<Enseignant> enseignant = enseignantService.authentifier(email, password);

            if (enseignant.isPresent()) {
                return ResponseEntity.ok(enseignant.get());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants invalides");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'authentification: " + e.getMessage());
        }
    }
}