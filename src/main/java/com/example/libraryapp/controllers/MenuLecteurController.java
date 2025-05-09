package com.example.libraryapp.controllers;

import com.example.libraryapp.model.utilisateurs.Lecteur;
import com.example.libraryapp.services.ViewLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuLecteurController implements Initializable {

    @FXML
    private Button btnVoirLivres, btnCatalogue, btnEmprunter, btnRetourner, btnDeconnexion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Ajouter les actions aux boutons
        btnVoirLivres.setOnAction(e -> voirLivresDisponibles());
        btnCatalogue.setOnAction(e -> consulterCatalogue());
        btnDeconnexion.setOnAction(e -> deconnexion());
    }

    private void voirLivresDisponibles() {
        Lecteur.voirLivresDisponibles();
    }

    private void consulterCatalogue() {
        Lecteur.consulterCatalogue();
    }


    private void deconnexion() {
        // Redirection vers l'écran d'acceuil
        ViewLoader.chargerVue("acceuil.fxml", "Library App");
    }
}
