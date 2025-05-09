package com.example.libraryapp.model.utilisateurs;

public abstract class Utilisateur {
    protected int id;
    protected String nom;
    protected String email;
    protected String motDePasse;
    protected String role;

    public Utilisateur(int id, String nom, String email, String motDePasse, String role) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    public abstract void afficherMenu();

    public String getNom() { return nom; }
    public String getEmail() { return email; }
    public int getId() { return id; }

    public void afficherInfos() {
        System.out.println("Nom: " + nom + " | Email: " + email);
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return nom + " " + email; // ou comme tu veux l’afficher
    }
}
