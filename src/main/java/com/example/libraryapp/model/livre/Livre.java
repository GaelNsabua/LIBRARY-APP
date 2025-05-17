package com.example.libraryapp.model.livre;

public abstract class Livre {
    protected int id;
    protected String titre;
    protected String auteur;
    protected int anneePublication;
    protected String isbn;
    protected String type;
    protected boolean disponible;

    public Livre(int id, String titre, String auteur, int anneePublication, String isbn, String type, boolean disponible) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.isbn = isbn;
        this.type = type;
        this.disponible = disponible;
    }

    public abstract void afficherDetails();

    public int getId() { return id; }
    public String getTitre() { return titre; }
    public String getAuteur() { return auteur; }
    public int getAnneePublication() { return anneePublication; }
    public String getIsbn() { return isbn; }
    public boolean isDisponible() { return disponible; }
    public String getType() { return type; }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return titre + " (" + auteur + ")";
    }
}
