package com.example.libraryapp.controllers;

import com.example.libraryapp.dao.EmpruntDao;
import com.example.libraryapp.dao.LivreDao;
import com.example.libraryapp.model.livre.Livre;
import com.example.libraryapp.model.utilisateurs.Bibliothecaire;
import com.example.libraryapp.services.ViewLoader;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class ListeLivreBiblioController{
    private final List<Livre> livres;

    @FXML private TableView<Livre> tableLivres;
    @FXML private TableColumn<Livre, String> colTitre;
    @FXML private TableColumn<Livre, String> colAuteur;
    @FXML private TableColumn<Livre, Integer> colAnnee;
    @FXML private TableColumn<Livre, String> colIsbn;
    @FXML private TableColumn<Livre, String> colType;


    public ListeLivreBiblioController(List<Livre> livres) {
        this.livres = livres;
    }

    @FXML
    public void initialize() {
        colTitre.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTitre()));
        colAuteur.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getAuteur()));
        colAnnee.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getAnneePublication()).asObject());
        colIsbn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getIsbn()));
        colType.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getType()));

        tableLivres.setItems(FXCollections.observableArrayList(livres));
    }

    @FXML
    public void handleRetourner() {
        Livre livreSelectionne = tableLivres.getSelectionModel().getSelectedItem();
        if (livreSelectionne != null) {
            int id = livreSelectionne.getId();
            EmpruntDao.retournerLivre(id);
            afficherAlerte("Livre retourné et remis en disponibilité.");
            Bibliothecaire.voirLivresEmpruntes();
        } else {
            afficherAlerte("Veuillez sélectionner un livre à modifier.");
        }
    }

    @FXML
    public void handleSupprimer() {
        Livre livreSelectionne = tableLivres.getSelectionModel().getSelectedItem();
        if (livreSelectionne != null) {
            int id = livreSelectionne.getId();
            LivreDao.supprimerLivre(id);
            tableLivres.getItems().remove(livreSelectionne);
        } else {
            afficherAlerte("Veuillez sélectionner un livre à supprimer.");
        }
    }

    private void afficherAlerte(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerte");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void handleReturn(){
        // Redirection vers l'écran d'acceuil
        ViewLoader.chargerVue("menu-biblio-view.fxml", "Menu Bibliothecaire");
    }
}
