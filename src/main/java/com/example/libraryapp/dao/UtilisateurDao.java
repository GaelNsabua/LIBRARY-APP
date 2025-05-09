package com.example.libraryapp.dao;


import com.example.libraryapp.model.livre.Livre;
import com.example.libraryapp.model.utilisateurs.Lecteur;
import com.example.libraryapp.model.utilisateurs.Utilisateur;
import com.example.libraryapp.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

        public static List<Lecteur> getLecteurs() {
            List<Lecteur> lecteurs = new ArrayList<>();
            String sql = "SELECT * FROM utilisateurs WHERE role = ?";
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql);) {
                ps.setString(1, "lecteur");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    lecteurs.add(new Lecteur(rs.getInt("id"), rs.getString("name"),
                            rs.getString("email"), rs.getString("password"), rs.getString("role")));
                    System.out.println(rs.getString("name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return lecteurs;
        }

    public static List<Lecteur> getAllUsers() {
        List<Lecteur> utilisateurs = new ArrayList<>();
        String sql = "SELECT * FROM utilisateurs WHERE role <> ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, "admin");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                utilisateurs.add(new Lecteur(rs.getInt("id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("password"),  rs.getString("role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
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