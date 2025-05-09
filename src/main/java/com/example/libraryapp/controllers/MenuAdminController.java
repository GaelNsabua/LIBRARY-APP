package com.example.libraryapp.controllers;

import com.example.libraryapp.model.utilisateurs.Admin;
import com.example.libraryapp.model.utilisateurs.Bibliothecaire;
import com.example.libraryapp.services.ViewLoader;
import javafx.fxml.FXML;

public class MenuAdminController {

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
    public void handleVoirUtilisateurs() {
        Admin.voirListeUtilisateur();
    }

    @FXML
    public void handleAjouterUtilisateur() {
        Admin.ajouterUtilisateur();
    }

    @FXML
    public void handleSupprimerUtilisateur() {
        Admin.supprimerUtilisateur();
    }

    @FXML
    private void deconnexion() {
        // Redirection vers l'écran d'acceuil
        ViewLoader.chargerVue("acceuil.fxml", "Library App");
    }
}
