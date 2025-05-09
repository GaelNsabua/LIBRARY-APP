package com.example.libraryapp.services;

import com.example.libraryapp.Main;
import com.example.libraryapp.controllers.ListeLivreBiblioController;
import com.example.libraryapp.controllers.ListeLivresController;
import com.example.libraryapp.controllers.ListeUtilisateurController;
import com.example.libraryapp.model.livre.Livre;
import com.example.libraryapp.model.utilisateurs.Lecteur;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.List;

public class ViewLoader {
    public static void chargerVue(String vuePath, String titre) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(vuePath));
            Scene scene = new Scene(fxmlLoader.load(), 500, 500);
            Stage stage = ((Stage) Stage.getWindows().filtered(Window::isShowing).getFirst());
            stage.setTitle(titre);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void chargerListeLivre(List<Livre> livres, String titre, String path) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(path));
            loader.setControllerFactory(type -> {
                if (type == ListeLivresController.class) {
                    return new ListeLivresController(livres);
                } else if (type == ListeLivreBiblioController.class) {
                    return new ListeLivreBiblioController(livres);
                } else {
                    try {
                        return type.getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            Scene scene = new Scene(loader.load(), 500, 500);
            Stage stage = ((Stage) Stage.getWindows().filtered(Window::isShowing).getFirst());
            stage.setTitle(titre);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public static void chargerListeUtilisateur(List<Lecteur> utilisateurs, String titre, String path) {
            try {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource(path));
                loader.setControllerFactory(type -> {
                    if (type == ListeUtilisateurController.class) {
                        return new ListeUtilisateurController(utilisateurs);
                    } else if (type == ListeLivreBiblioController.class) {
                        return new ListeUtilisateurController(utilisateurs);
                    } else {
                        try {
                            return type.getDeclaredConstructor().newInstance();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                Scene scene = new Scene(loader.load(), 500, 500);
                Stage stage = ((Stage) Stage.getWindows().filtered(Window::isShowing).getFirst());
                stage.setTitle(titre);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
