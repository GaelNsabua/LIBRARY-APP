package com.example.libraryapp.controllers;

import com.example.libraryapp.dao.LivreDao;
import com.example.libraryapp.model.livre.Livre;
import com.example.libraryapp.model.livre.LivreDAOHelper;
import com.example.libraryapp.model.utilisateurs.Bibliothecaire;
import com.example.libraryapp.model.utilisateurs.Lecteur;
import com.example.libraryapp.services.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

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
    private void enregistrerLivre() {
        try {
            String titre = fieldTitre.getText();
            String auteur = fieldAuteur.getText();
            int annee = Integer.parseInt(fieldAnnee.getText());
            String isbn = fieldIsbn.getText();

            Livre livre = LivreDAOHelper.createRomanTemp(titre, auteur, annee, isbn);
            LivreDao.ajouterLivre(livre);

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
