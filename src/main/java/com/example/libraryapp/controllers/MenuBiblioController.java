package com.example.libraryapp.controllers;

import com.example.libraryapp.model.utilisateurs.Bibliothecaire;
import com.example.libraryapp.services.ViewLoader;
import javafx.fxml.FXML;

public class MenuBiblioController {

    @FXML
    private void ajouterLivre() {
        Bibliothecaire.ajouterLivre();
    }

    @FXML
    private void supprimerLivre() {
        Bibliothecaire.supprimerLivre();
    }

    @FXML
    private void enregistrerEmprunt() {
        Bibliothecaire.enregistrerEmprunt();
    }

    @FXML
    private void enregistrerRetour() {
        Bibliothecaire.voirLivresEmpruntes();
    }

    @FXML
    private void voirLivresDisponibles() {
        Bibliothecaire.voirLivresDisponibles();
    }

    @FXML
    private void voirLivresEmpruntes() {
        Bibliothecaire.voirLivresEmpruntes();
    }

    @FXML
    private void ajouterUtilisateur(){
        Bibliothecaire.ajouterUtilisateur();
    }

    @FXML
    private void deconnexion() {
        // Redirection vers l'écran d'acceuil
        ViewLoader.chargerVue("acceuil.fxml", "Library App");
    }
}
