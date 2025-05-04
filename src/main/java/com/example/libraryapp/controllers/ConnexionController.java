package com.example.libraryapp.controllers;

import com.example.libraryapp.dao.EmpruntDao;
import com.example.libraryapp.model.utilisateurs.Utilisateur;
import com.example.libraryapp.services.Authentification;
import com.example.libraryapp.services.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ConnexionController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    @FXML
    protected void handleConnexion() {
        String email = emailField.getText().trim();
        String mdp = passwordField.getText();

        Utilisateur user = Authentification.seConnecter(email, mdp);
        if (user != null) {
            messageLabel.setText("Bienvenue " + user.getNom());

            EmpruntDao.verifierEtSanctionnerRetards();

            user.afficherMenu(); // à adapter en interface graphique
        } else {
            messageLabel.setText("Email ou mot de passe incorrect.");
        }
    }

    @FXML
    public void handleReturn(){
        // Redirection vers l'écran d'acceuil
        ViewLoader.chargerVue("acceuil.fxml", "Library App");
    }
}
