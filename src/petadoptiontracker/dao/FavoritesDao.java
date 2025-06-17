/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petadoptiontracker.dao;

import java.sql.*;
import java.util.*;
import petadoptiontracker.database.MySqlConnection;
import petadoptiontracker.model.PetModel;

public class FavoritesDao {
    MySqlConnection mySql = new MySqlConnection();

    public boolean addToFavorites(int userId, int petId) {
        String sql = "INSERT IGNORE INTO Favorites (user_id, pet_id) VALUES (?, ?)";
        try (Connection conn = mySql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, petId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<PetModel> getFavoritesByUser(int userId) {
        List<PetModel> favorites = new ArrayList<>();
        String sql = "SELECT p.* FROM Pets p JOIN Favorites f ON p.id = f.pet_id WHERE f.user_id = ?";
        try (Connection conn = mySql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PetModel pet = new PetModel();
                pet.setId(rs.getInt("id"));
                pet.setName(rs.getString("name"));
                pet.setBreed(rs.getString("breed"));
                pet.setAge(rs.getInt("age"));
                pet.setSex(rs.getString("sex"));
                pet.setStatus(rs.getString("status"));
                favorites.add(pet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return favorites;
    }
}
