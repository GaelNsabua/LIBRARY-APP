package com.example.libraryapp.dao;


import com.example.libraryapp.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UtilisateurDao {
    public static void ajouterUtilisateur(String nom, String email, String mdp, String role) {
        String sql = "INSERT INTO utilisateurs(name, email, password, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nom);
            ps.setString(2, email);
            ps.setString(3, mdp);
            ps.setString(4, role.toUpperCase());
            ps.executeUpdate();
            System.out.println("Utilisateur ajoute.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void supprimerUtilisateur(int id) {
        String sql = "DELETE FROM utilisateurs WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Utilisateur supprime.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}