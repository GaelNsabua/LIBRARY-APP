package com.example.libraryapp.model.utilisateurs;

import com.example.libraryapp.dao.EmpruntDao;
import com.example.libraryapp.dao.LivreDao;
import com.example.libraryapp.dao.UtilisateurDao;
import com.example.libraryapp.model.livre.Livre;
import com.example.libraryapp.model.livre.LivreDAOHelper;

import java.util.List;
import java.util.Scanner;

public class Admin extends Utilisateur {
    public Admin(int id, String nom, String email, String motDePasse) {
        super(id, nom, email, motDePasse);
    }

    @Override
    public void afficherMenu() {
        Scanner sc = new Scanner(System.in);
        int choix;
        do {
            System.out.println("\nMENU ADMIN");
            System.out.println("===========");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Supprimer un livre");
            System.out.println("3. Voir livres disponibles");
            System.out.println("4. Voir livres empruntés");
            System.out.println("5. Ajouter un bibliothécaire");
            System.out.println("6. Supprimer un bibliothécaire");
            System.out.println("0. Déconnexion");
            System.out.print("Choix: ");
            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1 -> ajouterLivre(sc);
                case 2 -> supprimerLivre(sc);
                case 3 -> voirLivresDisponibles();
                case 4 -> voirLivresEmpruntes();
                case 5 -> ajouterBibliothecaire(sc);
                case 6 -> supprimerBibliothecaire(sc);
            }
        } while (choix != 0);
    }

    private void ajouterLivre(Scanner sc) {
        System.out.println("Ajout d'un livre\n");

        System.out.print("Titre: ");
        String titre = sc.nextLine();
        System.out.print("Auteur: ");
        String auteur = sc.nextLine();
        System.out.print("Année publication: ");
        int annee = sc.nextInt(); sc.nextLine();
        System.out.print("ISBN: ");
        String isbn = sc.nextLine();

        Livre livre = LivreDAOHelper.createRomanTemp(titre, auteur, annee, isbn);
        LivreDao.ajouterLivre(livre);
    }

    private void supprimerLivre(Scanner sc) {
        System.out.print("ID du livre à supprimer: ");
        int id = sc.nextInt();
        LivreDao.supprimerLivre(id);
    }

    private void voirLivresDisponibles() {
        List<Livre> livres = LivreDao.getLivresDisponibles();
        if (livres.isEmpty()) {
            System.out.println("Aucun livre disponible.");
        } else {
            System.out.println("Livres disponibles :");
            livres.forEach(Livre::afficherDetails);
        }
    }

    private void voirLivresEmpruntes() {
        EmpruntDao.afficherEmpruntsActifs();
    }

    private void ajouterBibliothecaire(Scanner sc) {
        System.out.print("Nom : ");
        String nom = sc.nextLine();
        System.out.print("Email : ");
        String email = sc.nextLine();
        System.out.print("Mot de passe : ");
        String mdp = sc.nextLine();
        UtilisateurDao.ajouterUtilisateur(nom, email, mdp, "bibliothecaire");
    }

    private void supprimerBibliothecaire(Scanner sc) {
        System.out.print("ID du bibliothécaire à supprimer : ");
        int id = sc.nextInt();
        UtilisateurDao.supprimerUtilisateur(id);
    }
}
