package com.example.libraryapp.model.utilisateurs;

import com.example.libraryapp.dao.EmpruntDao;
import com.example.libraryapp.dao.LivreDao;
import com.example.libraryapp.model.livre.Livre;
import com.example.libraryapp.services.ViewLoader;

import java.util.List;
import java.util.Scanner;

public class Lecteur extends Utilisateur {
    public Lecteur(int id, String nom, String email, String motDePasse, String role) {
        super(id, nom, email, motDePasse, role);
    }

    @Override
    public void afficherMenu() {
        ViewLoader.chargerVue("menu-lecteur.fxml", "Menu Lecteur");
    }

    public static void voirLivresDisponibles() {
        List<Livre> livres = LivreDao.getLivresDisponibles();
        if (livres.isEmpty()) {
            ViewLoader.chargerVue("menu-lecteur.fxml", "Menu Lecteur");
        } else {
            ViewLoader.chargerListeLivre(livres, "Liste des livres disponibles", "liste-livres-view.fxml");
        }
    }

    public static void consulterCatalogue() {
        List<Livre> livres = LivreDao.getAllLivres();
        if (livres.isEmpty()) {
            ViewLoader.chargerVue("menu-lecteur.fxml", "Menu Lecteur");
        } else {
            ViewLoader.chargerListeLivre(livres, "Liste des livres disponibles", "liste-livres-view.fxml");
        }
    }

    private void emprunterLivre(Scanner sc) {
        System.out.print("ID du livre à emprunter : ");
        int idLivre = sc.nextInt();
        EmpruntDao.enregistrerEmprunt(idLivre, this.id);
    }

    private void retournerLivre(Scanner sc) {
        System.out.print("ID de l'emprunt à retourner : ");
        int idEmprunt = sc.nextInt();
        EmpruntDao.retournerLivre(idEmprunt);
    }
}
