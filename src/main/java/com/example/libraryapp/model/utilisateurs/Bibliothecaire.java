package com.example.libraryapp.model.utilisateurs;

import com.example.libraryapp.dao.EmpruntDao;
import com.example.libraryapp.dao.LivreDao;
import com.example.libraryapp.model.livre.Livre;
import com.example.libraryapp.model.livre.LivreDAOHelper;
import com.example.libraryapp.services.ViewLoader;

import java.util.List;
import java.util.Scanner;

public class Bibliothecaire extends Utilisateur {
    public Bibliothecaire(int id, String nom, String email, String motDePasse, String role) {
        super(id, nom, email, motDePasse, role);
    }

    @Override
    public void afficherMenu() {
        ViewLoader.chargerVue("menu-biblio-view.fxml", "Menu Bibliothecaire");
    }

    public static void ajouterLivre() {
        ViewLoader.chargerVue("ajout-livre-view.fxml", "Menu Ajouter un livre");
    }

    public static void supprimerLivre() {
        List<Livre> livres = LivreDao.getAllLivres();
        if (livres.isEmpty()) {
            ViewLoader.chargerVue("menu-biblio-view.fxml", "Menu Bibliothecaire");
        } else {
            ViewLoader.chargerListeLivre(livres, "Supprimer un livre", "liste-livres-biblio-view.fxml");
        }
    }

    public static void enregistrerEmprunt() {
        ViewLoader.chargerVue("save-emprunt-view.fxml", "Emprunt de livre");
    }

    public static void voirLivresDisponibles() {
        List<Livre> livres = LivreDao.getLivresDisponibles();
        if (livres.isEmpty()) {
            ViewLoader.chargerVue("menu-biblio-view.fxml", "Menu Bibliothecaire");
        } else {
            ViewLoader.chargerListeLivre(livres, "Liste des livres disponibles", "liste-livres-biblio-view.fxml");
        }
    }

    public static void voirLivresEmpruntes() {
        List<Livre> livres = LivreDao.getLivresEmpruntes();
        if (livres.isEmpty()) {
            ViewLoader.chargerVue("menu-biblio-view.fxml", "Menu Bibliothecaire");
        } else {
            ViewLoader.chargerListeLivre(livres, "Liste des livres Empruntés", "liste-livres-biblio-view.fxml");
        }
    }

    public static void ajouterUtilisateur(){
        ViewLoader.chargerVue("inscription.fxml", "Ajouter un lecteur");
    }
}
