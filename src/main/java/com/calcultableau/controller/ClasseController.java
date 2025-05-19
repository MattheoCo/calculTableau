package com.calcultableau.controller;

import com.calcultableau.model.Classe;
import com.calcultableau.model.Note;
import com.calcultableau.service.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClasseController {

    private final ClasseService classeService;

    @Autowired
    public ClasseController(ClasseService classeService) {
        this.classeService = classeService;
    }

    @GetMapping
    public ResponseEntity<List<Classe>> getAllClasses() {
        try {
            return ResponseEntity.ok(classeService.getAllClasses());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClasseById(@PathVariable Long id) {
        try {
            return classeService.getClasseById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{classeId}/matieres/{matiereId}/notes")
    public ResponseEntity<?> mettreAJourNote(
            @PathVariable Long classeId,
            @PathVariable Long matiereId,
            @RequestBody Note note) {
        try {
            classeService.mettreAJourNotes(classeId, matiereId, note);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la mise à jour des notes: " + e.getMessage());
        }
    }
}