package com.example.libraryapp.controllers;

import com.example.libraryapp.dao.UtilisateurDao;
import com.example.libraryapp.services.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class InscriptionController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField mdpField;

    @FXML
    private Label messageLabel;

    @FXML
    public void handleInscription() {
        String nom = nomField.getText().trim();
        String email = emailField.getText().trim();
        String mdp = mdpField.getText();

        if (nom.isEmpty() || email.isEmpty() || mdp.isEmpty()) {
            messageLabel.setText("Veuillez remplir tous les champs.");
            return;
        }

        UtilisateurDao.ajouterUtilisateur(nom, email, mdp, "lecteur");
        messageLabel.setText("Inscription réussie !");
        nomField.clear();
        emailField.clear();
        mdpField.clear();

        // Redirection vers l'écran de connexion
        ViewLoader.chargerVue("connexion.fxml", "Connexion à la bibliothèque");
    }

    @FXML
    public void handleReturn(){
        // Redirection vers l'écran d'acceuil
        ViewLoader.chargerVue("acceuil.fxml", "Library App");
    }
}