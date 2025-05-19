package com.calcultableau.controller;

import com.calcultableau.model.Enseignant;
import com.calcultableau.service.EnseignantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthenticationController.class)
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnseignantService enseignantService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testLogin_Success() throws Exception {
        // Préparation
        Enseignant enseignant = new Enseignant(1L, "Jean", "Dupont", "jean@test.com", "password");
        when(enseignantService.authentifier("jean@test.com", "password"))
                .thenReturn(Optional.of(enseignant));

        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", "jean@test.com");
        credentials.put("password", "password");

        // Test
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(credentials)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.prenom").value("Jean"))
                .andExpect(jsonPath("$.nom").value("Dupont"));
    }

    @Test
    void testLogin_Failure() throws Exception {
        // Préparation
        when(enseignantService.authentifier(anyString(), anyString()))
                .thenReturn(Optional.empty());

        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", "jean@test.com");
        credentials.put("password", "wrongpassword");

        // Test
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(credentials)))
                .andExpect(status().isUnauthorized());
    }
}