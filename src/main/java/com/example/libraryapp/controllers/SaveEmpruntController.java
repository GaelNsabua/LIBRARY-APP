package com.example.libraryapp.controllers;

import com.example.libraryapp.dao.EmpruntDao;
import com.example.libraryapp.dao.LivreDao;
import com.example.libraryapp.dao.UtilisateurDao;
import com.example.libraryapp.model.livre.Livre;
import com.example.libraryapp.model.utilisateurs.Bibliothecaire;
import com.example.libraryapp.model.utilisateurs.Lecteur;
import com.example.libraryapp.services.ViewLoader;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.List;

public class SaveEmpruntController {

    @FXML private ComboBox<Lecteur> comboLecteurs;
    @FXML private ComboBox<Livre> comboLivres;

    @FXML
    public void initialize() {
        // Charger les lecteurs éligibles
        List<Lecteur> lecteurs = UtilisateurDao.getLecteurs();
        comboLecteurs.setItems(FXCollections.observableArrayList(lecteurs));

        // Charger les livres disponibles
        List<Livre> livresDispos = LivreDao.getLivresDisponibles();
        comboLivres.setItems(FXCollections.observableArrayList(livresDispos));
    }

    @FXML
    public void handleEnregistrerEmprunt() {
        Lecteur lecteur = comboLecteurs.getValue();
        Livre livre = comboLivres.getValue();

        if (lecteur == null || livre == null) {
            afficherAlerte("Erreur", "Veuillez sélectionner un lecteur et un livre.", Alert.AlertType.WARNING);
            return;
        }

        EmpruntDao.enregistrerEmprunt(livre.getId(), lecteur.getId());
        afficherAlerte("Succès", "Emprunt enregistré avec succès !", Alert.AlertType.INFORMATION);

        comboLivres.getItems().remove(livre); // Retirer le livre de la liste
        comboLivres.setValue(null);
        comboLecteurs.setValue(null);

        Bibliothecaire.voirLivresEmpruntes();
    }

    @FXML
    public void handleReturn(){
        // Redirection vers l'écran d'acceuil
        ViewLoader.chargerVue("menu-biblio-view.fxml", "Menu Bibliothecaire");
    }

    private void afficherAlerte(String titre, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
