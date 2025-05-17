package com.example.libraryapp.model.livre;

public class ScienceFiction extends Livre {
    private String univers;

    public ScienceFiction(int id, String titre, String auteur, int annee, String isbn, boolean dispo, String univers) {
        super(id, titre, auteur, annee, isbn, "science_fiction", dispo);
        this.univers = univers;
    }

    @Override
    public void afficherDetails() {
        System.out.println("Science-Fiction: " + titre + " | Univers: " + univers + " | ISBN: " + isbn);
    }
}
