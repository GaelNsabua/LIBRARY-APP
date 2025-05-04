package com.example.libraryapp.controllers;

import com.example.libraryapp.model.livre.Livre;
import com.example.libraryapp.services.ViewLoader;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class ListeLivresController {

    private final List<Livre> livres;

    @FXML private TableView<Livre> tableLivres;
    @FXML private TableColumn<Livre, String> colTitre;
    @FXML private TableColumn<Livre, String> colAuteur;
    @FXML private TableColumn<Livre, Integer> colAnnee;
    @FXML private TableColumn<Livre, String> colIsbn;
    @FXML private TableColumn<Livre, String> colType;


    public ListeLivresController(List<Livre> livres) {
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
    public void handleReturn(){
        // Redirection vers l'écran d'acceuil
        ViewLoader.chargerVue("menu-lecteur.fxml", "Menu Lecteur");
    }
}