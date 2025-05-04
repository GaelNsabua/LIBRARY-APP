package com.example.libraryapp.services;

import com.example.libraryapp.model.utilisateurs.Admin;
import com.example.libraryapp.model.utilisateurs.Bibliothecaire;
import com.example.libraryapp.model.utilisateurs.Lecteur;
import com.example.libraryapp.model.utilisateurs.Utilisateur;
import com.example.libraryapp.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authentification {
    public static Utilisateur seConnecter(String email, String motDePasse) {
        String sql = "SELECT * FROM utilisateurs WHERE email = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, motDePasse);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("name");
                String role = rs.getString("role");

                switch (role.toLowerCase()) {
                    case "admin":
                        return new Admin(id, nom, email, motDePasse);
                    case "bibliothecaire":
                        return new Bibliothecaire(id, nom, email, motDePasse);
                    case "lecteur":
                        return new Lecteur(id, nom, email, motDePasse);
                    default:
                        return null;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur Authentification : " + e.getMessage());
        }
        return null;
    }
}
