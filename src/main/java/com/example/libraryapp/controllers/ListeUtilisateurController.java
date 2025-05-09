package com.example.libraryapp.controllers;


import com.example.libraryapp.dao.LivreDao;
import com.example.libraryapp.dao.UtilisateurDao;
import com.example.libraryapp.model.livre.Livre;
import com.example.libraryapp.model.utilisateurs.Lecteur;
import com.example.libraryapp.model.utilisateurs.Utilisateur;
import com.example.libraryapp.services.ViewLoader;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class ListeUtilisateurController {

    private final List<Lecteur> utilisateurs;

    public ListeUtilisateurController(List<Lecteur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    @FXML
    private TableView<Lecteur> tableUtilisateurs;
    @FXML
    private TableColumn<Lecteur, Integer> colId;
    @FXML
    private TableColumn<Lecteur, String> colNom;
    @FXML
    private TableColumn<Lecteur, String> colEmail;
    @FXML
    private TableColumn<Lecteur, String> colRole;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colNom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        colRole.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole()));

        tableUtilisateurs.setItems(FXCollections.observableArrayList(utilisateurs));
    }

    @FXML
    public void handleSupprimer() {
        Lecteur utilisateurSelectionne = tableUtilisateurs.getSelectionModel().getSelectedItem();
        if (utilisateurSelectionne != null) {
            int id = utilisateurSelectionne.getId();
            UtilisateurDao.supprimerUtilisateur(id);
            tableUtilisateurs.getItems().remove(utilisateurSelectionne);
            afficherAlerte("Succès", "Utilisateur supprimer avec succès !", Alert.AlertType.INFORMATION);
        } else {
            afficherAlerte("Erreur", "Veuillez selectionner un utilisateur !", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleRetour() {
        ViewLoader.chargerVue("menu-admin-view.fxml", "Menu Admin");
    }

    private void afficherAlerte(String titre, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
