/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petadoptiontracker.dao;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import petadoptiontracker.database.MySqlConnection;
import petadoptiontracker.model.PetModel;
import petadoptiontracker.model.UserData;

public class AdminDao {
    MySqlConnection mySql = new MySqlConnection();
    
    // Add Pet (with table creation if not exists)
    public boolean addPet(PetModel pet) {
        Connection conn = mySql.openConnection();
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Pets ("
            + "id INT AUTO_INCREMENT PRIMARY KEY, "
            + "name VARCHAR(50) NOT NULL, "
            + "breed VARCHAR(50) NOT NULL, "
            + "age INT NOT NULL, "
            + "sex VARCHAR(10) NOT NULL, "
            + "photo BLOB, "
            + "photo2 BLOB, "
            + "photo3 BLOB, "
            + "status VARCHAR(20) DEFAULT 'Available'"
            + ")";
        
        String query = "INSERT INTO Pets (name, breed, age, sex, photo,photo2,photo3, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            // Create pets table if not exists
            try (PreparedStatement createtbl = conn.prepareStatement(createTableSQL)) {
                createtbl.executeUpdate();
            }
            
            // Insert pet data
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, pet.getName());
                pstmt.setString(2, pet.getBreed());
                pstmt.setInt(3, pet.getAge());
                pstmt.setString(4, pet.getSex());
                pstmt.setBytes(5, pet.getPhoto());
                pstmt.setBytes(6, pet.getPhoto2());
                pstmt.setBytes(7, pet.getPhoto3());
                pstmt.setString(8, pet.getStatus() != null ? pet.getStatus() : "Available");
                
                
                int result = pstmt.executeUpdate();
                return result > 0;
            }
        } catch (SQLException ex) {
            System.err.println(ex);
            return false;
        } finally {
            mySql.closeConnection(conn);
        }
    }

    // Fetch admin by email
    public UserData getAdminByEmail(String email) {
        String sql = "SELECT * FROM Users WHERE email = ? AND role = 'ADMIN'";
        try (Connection conn = mySql.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                UserData admin = new UserData(
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getBytes("image"),
                    rs.getString("role")
                );
                admin.setId(rs.getInt("id"));
                return admin;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Read (Get All Pets)
    public List<PetModel> getAllPets() {
        List<PetModel> pets = new ArrayList<>();
        String sql = "SELECT * FROM pets";
        try (Connection conn = mySql.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                PetModel pet = new PetModel();
                pet.setId(rs.getInt("id"));
                pet.setName(rs.getString("name"));
                pet.setBreed(rs.getString("breed"));
                pet.setAge(rs.getInt("age"));
                pet.setSex(rs.getString("sex"));
                //pet.setPhoto(rs.getBytes("photo"));
                pet.setStatus(rs.getString("status"));
                pets.add(pet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pets;
    }

    // Update (Edit Pet)
    public boolean updatePet(PetModel pet) {
        String sql = "UPDATE pets SET name=?, breed=?, age=?, sex=?, photo=?, status=? WHERE id=?";
        try (Connection conn = mySql.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pet.getName());
            pstmt.setString(2, pet.getBreed());
            pstmt.setInt(3, pet.getAge());
            pstmt.setString(4, pet.getSex());
            pstmt.setBytes(5, pet.getPhoto());
            pstmt.setString(6, pet.getStatus());
            pstmt.setInt(7, pet.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Delete (Remove Pet)
    public boolean deletePet(int petId) {
        String sql = "DELETE FROM pets WHERE id=?";
        try (Connection conn = mySql.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, petId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Get Pet by ID
    public PetModel getPetById(int petId) {
        String sql = "SELECT * FROM pets WHERE id=?";
        try (Connection conn = mySql.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, petId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    PetModel pet = new PetModel();
                    pet.setId(rs.getInt("id"));
                    pet.setName(rs.getString("name"));
                    pet.setBreed(rs.getString("breed"));
                    pet.setAge(rs.getInt("age"));
                    pet.setSex(rs.getString("sex"));
                    pet.setPhoto(rs.getBytes("photo"));
                    pet.setPhoto2(rs.getBytes("photo2"));
                    pet.setPhoto3(rs.getBytes("photo3"));
                    pet.setStatus(rs.getString("status"));
                    return pet;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
