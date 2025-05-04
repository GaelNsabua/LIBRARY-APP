package com.example.libraryapp.controllers;

import com.example.libraryapp.services.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AccueilController {

    @FXML
    public void handleSeConnecter(ActionEvent event) {
        ViewLoader.chargerVue("connexion.fxml", "Connexion à la bibliothèque");
    }

    @FXML
    public void handleSinscrire(ActionEvent event) {
        ViewLoader.chargerVue("inscription.fxml", "Inscription à la bibliothèque");
    }

    @FXML
    public void handleQuitter(ActionEvent event) {
        System.exit(0);
    }
}
