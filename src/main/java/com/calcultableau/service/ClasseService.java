package com.calcultableau.service;

import com.calcultableau.model.Classe;
import com.calcultableau.model.Matiere;
import com.calcultableau.model.Note;
import com.calcultableau.model.Statistiques;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClasseService {

    private final JsonPersistenceService persistenceService;
    private final CalculTab calculTab;
    private static final String CLASSES_FILE = "data/classes.json";

    @Autowired
    public ClasseService(JsonPersistenceService persistenceService, CalculTab calculTab) {
        this.persistenceService = persistenceService;
        this.calculTab = calculTab;
    }

    public List<Classe> getAllClasses() throws IOException {
        Map<String, List<Classe>> data = persistenceService.chargerDonnees(
                CLASSES_FILE,
                new TypeReference<HashMap<String, List<Classe>>>() {}
        );
        return data.get("classes");
    }

    public Optional<Classe> getClasseById(Long id) throws IOException {
        return getAllClasses().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public void mettreAJourNotes(Long classeId, Long matiereId, Note note) throws IOException {
        List<Classe> classes = getAllClasses();

        for (Classe classe : classes) {
            if (classe.getId().equals(classeId)) {
                for (Matiere matiere : classe.getMatieres()) {
                    if (matiere.getId().equals(matiereId)) {
                        // Mise à jour ou ajout de la note
                        List<Note> notes = matiere.getNotes();
                        notes.removeIf(n -> n.getEtudiantId().equals(note.getEtudiantId()));
                        notes.add(note);

                        // Recalcul des statistiques
                        calculTab.viderNotes();
                        for (Note n : notes) {
                            for (Integer valeur : n.getValeurs()) {
                                calculTab.ajouterNote(valeur);
                            }
                        }

                        Statistiques stats = new Statistiques(
                                LocalDate.now().toString(),
                                calculTab.calculerMoyenne(),
                                calculTab.calculerMediane()
                        );
                        matiere.setStatistiques(stats);
                        break;
                    }
                }
                break;
            }
        }

        // Sauvegarde des modifications
        Map<String, List<Classe>> data = new HashMap<>();
        data.put("classes", classes);
        persistenceService.sauvegarderDonnees(CLASSES_FILE, data);
    }
}