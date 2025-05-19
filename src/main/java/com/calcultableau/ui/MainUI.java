package com.calcultableau.ui;

import com.calcultableau.service.CalculService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Créer les composants de l'interface
        Label label = new Label("Entre des nombres séparés par des virgules :");
        TextField inputField = new TextField();
        Button calcButton = new Button("Calculer la somme");
        Label resultLabel = new Label();

        // Action quand on clique sur le bouton
        calcButton.setOnAction(e -> {
            String inputText = inputField.getText();
            String[] parts = inputText.split(",");
            int[] tableau = new int[parts.length];

            try {
                for (int i = 0; i < parts.length; i++) {
                    tableau[i] = Integer.parseInt(parts[i].trim());
                }

                // Appel du service métier
                CalculService service = new CalculService();
                int resultat = service.calculerSomme(tableau); // on suppose que cette méthode existe
                resultLabel.setText("Résultat : " + resultat);

            } catch (NumberFormatException ex) {
                resultLabel.setText("Erreur : entre uniquement des nombres !");
            }
        });

        // Disposition verticale
        VBox root = new VBox(10, label, inputField, calcButton, resultLabel);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(root, 400, 200);

        primaryStage.setTitle("Calcul de Somme - JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
