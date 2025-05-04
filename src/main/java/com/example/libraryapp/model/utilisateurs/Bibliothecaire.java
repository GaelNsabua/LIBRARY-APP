package com.example.libraryapp.model.utilisateurs;

import com.example.libraryapp.dao.EmpruntDao;
import com.example.libraryapp.dao.LivreDao;
import com.example.libraryapp.model.livre.Livre;
import com.example.libraryapp.model.livre.LivreDAOHelper;

import java.util.List;
import java.util.Scanner;

public class Bibliothecaire extends Utilisateur {
    public Bibliothecaire(int id, String nom, String email, String motDePasse) {
        super(id, nom, email, motDePasse);
    }

    @Override
    public void afficherMenu() {
        Scanner sc = new Scanner(System.in);
        int choix;
        do {
            System.out.println("\nMENU BIBLIOTHÉCAIRE");
            System.out.println("=====================");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Supprimer un livre");
            System.out.println("3. Enregistrer un emprunt");
            System.out.println("4. Appliquer une sanction");
            System.out.println("5. Voir livres disponibles");
            System.out.println("6. Voir livres empruntés");
            System.out.println("0. Déconnexion");
            System.out.print("Choix: ");
            choix = sc.nextInt(); sc.nextLine();

            switch (choix) {
                case 1 -> ajouterLivre(sc);
                case 2 -> supprimerLivre(sc);
                case 3 -> enregistrerEmprunt(sc);
                case 4 -> appliquerSanction(sc);
                case 5 -> voirLivresDisponibles();
                case 6 -> voirLivresEmpruntes();
            }
        } while (choix != 0);
    }

    private void ajouterLivre(Scanner sc) {
        System.out.println("Ajouter un livre\n");

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

    private void enregistrerEmprunt(Scanner sc) {
        System.out.print("ID du lecteur: ");
        int idLecteur = sc.nextInt();
        System.out.print("ID du livre: ");
        int idLivre = sc.nextInt();

        EmpruntDao.enregistrerEmprunt(idLivre, idLecteur);
    }

    private void appliquerSanction(Scanner sc) {
        EmpruntDao.verifierEtSanctionnerRetards();
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
}
