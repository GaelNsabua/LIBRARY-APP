package com.example.libraryapp.model.utilisateurs;

import com.example.libraryapp.dao.EmpruntDao;
import com.example.libraryapp.dao.LivreDao;
import com.example.libraryapp.dao.UtilisateurDao;
import com.example.libraryapp.model.livre.Livre;
import com.example.libraryapp.model.livre.LivreDAOHelper;
import com.example.libraryapp.services.ViewLoader;

import java.util.List;
import java.util.Scanner;

public class Admin extends Utilisateur {
    public Admin(int id, String nom, String email, String motDePasse, String role) {
        super(id, nom, email, motDePasse, role);
    }

    @Override
    public void afficherMenu() {
        ViewLoader.chargerVue("menu-admin-view.fxml", "Menu Administrateur");
    }

    public static void ajouterUtilisateur() {
        ViewLoader.chargerVue("ajout-utilisateur-view.fxml", "Enregistrement Utilisateur");
    }

    public static void voirListeUtilisateur(){
        List<Lecteur> utilisateurs = UtilisateurDao.getAllUsers();
        if (utilisateurs.isEmpty()) {
            ViewLoader.chargerVue("menu-admin-view.fxml", "Menu Administrateur");
        } else {
            ViewLoader.chargerListeUtilisateur(utilisateurs, "Liste des utilisateurs", "liste-utilisateurs-view.fxml");
        }
    }

    public static void supprimerUtilisateur(){
        List<Lecteur> utilisateurs = UtilisateurDao.getAllUsers();
        if (utilisateurs.isEmpty()) {
            ViewLoader.chargerVue("menu-admin-view.fxml", "Menu Administrateur");
        } else {
            ViewLoader.chargerListeUtilisateur(utilisateurs, "Supprimer un utilisateurs", "liste-utilisateurs-view.fxml");
        }
    }
}
