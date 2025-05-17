package com.example.libraryapp.controllers;

import com.example.libraryapp.dao.LivreDao;
import com.example.libraryapp.dao.UtilisateurDao;
import com.example.libraryapp.model.livre.Livre;
import com.example.libraryapp.model.utilisateurs.Lecteur;
import com.example.libraryapp.services.ViewLoader;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;


public class AjouterUtilisateurController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField mdpField;

    @FXML private ComboBox<String> role;

    @FXML
    public void initialize() {
        // Charger les roles utilisateur
        List<String> roles = new ArrayList<>();
        roles.add("lecteur");
        roles.add("bibliothecaire");
        role.setItems(FXCollections.observableArrayList(roles));
    }

    @FXML
    public void handleInscription() {
        String nom = nomField.getText().trim();
        String email = emailField.getText().trim();
        String mdp = mdpField.getText();
        String UserRole = role.getValue();

        if (nom.isEmpty() || email.isEmpty() || mdp.isEmpty() || UserRole == null) {
            afficherAlerte("Erreur", "Veuillez remplir tous les champs.", Alert.AlertType.ERROR);
            return;
        }

        UtilisateurDao.ajouterUtilisateur(nom, email, mdp, UserRole);
        afficherAlerte("Succès", "Inscription réussie.", Alert.AlertType.CONFIRMATION);
        nomField.clear();
        emailField.clear();
        mdpField.clear();

        // Redirection vers la liste des utilisateur
        //ViewLoader.chargerVue("connexion.fxml", "Connexion à la bibliothèque");
    }

    @FXML
    public void handleReturn(){
        // Redirection vers l'écran d'acceuil
        ViewLoader.chargerVue("menu-admin-view.fxml", "Menu Administrateur");
    }

    private void afficherAlerte(String titre, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}