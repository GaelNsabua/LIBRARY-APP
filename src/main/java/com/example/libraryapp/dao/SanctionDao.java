package com.example.libraryapp.dao;

import com.example.libraryapp.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SanctionDao {
    public static void enregistrerSanction(int idEmprunt, int jours, double montant) {
        String description = "Retourne en retard";
        String sql = "INSERT INTO sanctions(id_emprunt, jours_retard, montant) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEmprunt);
            ps.setInt(2, jours);
            ps.setDouble(3, montant);
            ps.executeUpdate();
            System.out.println("Sanction enregistree.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}