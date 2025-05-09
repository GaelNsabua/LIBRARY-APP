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

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Bienvenue " + user.getNom());
            alert.showAndWait();

            EmpruntDao.verifierEtSanctionnerRetards();

            user.afficherMenu(); // à adapter en interface graphique
        } else {
            handleError("Email ou mot de passe incorrect.");
        }
    }

    @FXML
    public void handleReturn(){
        // Redirection vers l'écran d'acceuil
        ViewLoader.chargerVue("acceuil.fxml", "Library App");
    }

    private void handleError(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText(message);
        alert.showAndWait();
    }
}


