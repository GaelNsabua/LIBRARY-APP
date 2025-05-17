package com.example.libraryapp.controllers;

import com.example.libraryapp.dao.LivreDao;
import com.example.libraryapp.model.livre.Livre;
import com.example.libraryapp.model.livre.LivreDAOHelper;
import com.example.libraryapp.model.utilisateurs.Bibliothecaire;
import com.example.libraryapp.model.utilisateurs.Lecteur;
import com.example.libraryapp.services.ViewLoader;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class AjouterLivreController {

    @FXML
    private TextField fieldTitre;
    @FXML
    private TextField fieldAuteur;
    @FXML
    private TextField fieldAnnee;
    @FXML
    private TextField fieldIsbn;

    @FXML
    private ComboBox<String> type;

    @FXML
    public void initialize() {
        // Charger les roles utilisateur
        List<String> types = new ArrayList<>();
        types.add("roman");
        types.add("biographie");
        types.add("magazine");
        types.add("sience-fiction");
        type.setItems(FXCollections.observableArrayList(types));
    }


    @FXML
    private void enregistrerLivre() {
        try {
            String titre = fieldTitre.getText();
            String auteur = fieldAuteur.getText();
            int annee = Integer.parseInt(fieldAnnee.getText());
            String isbn = fieldIsbn.getText();
            String typo = type.getValue().toUpperCase();

            LivreDao.ajouterLivre(titre, auteur, annee, isbn, typo);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Livre ajouté avec succès !");
            alert.showAndWait();

            // Réinitialiser les champs
            fieldTitre.clear();
            fieldAuteur.clear();
            fieldAnnee.clear();
            fieldIsbn.clear();

            Bibliothecaire.voirLivresDisponibles();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Format invalide");
            alert.setContentText("L'année de publication doit être un nombre entier.");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleReturn(){
        // Redirection vers l'écran d'acceuil
        ViewLoader.chargerVue("menu-biblio-view.fxml", "Menu Bibliothecaire");
    }
}
