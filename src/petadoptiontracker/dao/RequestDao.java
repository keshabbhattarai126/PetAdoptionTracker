/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petadoptiontracker.dao;

import java.sql.Connection;
import petadoptiontracker.database.MySqlConnection;
import petadoptiontracker.model.Request;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 *
 * @author LeathLOQ
 */
public class RequestDao {
    MySqlConnection mySql = new MySqlConnection();

    public boolean createRequest(int userId, int petId) {
        Connection conn = mySql.openConnection();
        String createTableSQL = "CREATE TABLE IF NOT EXISTS adoption_requests ("
            + "id INT AUTO_INCREMENT PRIMARY KEY, "
            + "user_id INT NOT NULL, "
            + "pet_id INT NOT NULL, "
            + "status VARCHAR(20) DEFAULT 'Pending', "
            + "request_date DATETIME DEFAULT CURRENT_TIMESTAMP, "
            + "FOREIGN KEY (user_id) REFERENCES users(id), "
            + "FOREIGN KEY (pet_id) REFERENCES pets(id)"
            + ")";
        String insertSQL = "INSERT INTO adoption_requests (user_id, pet_id, status) VALUES (?, ?, ?)";

        try {
            // Create table if it doesn't exist
            try (PreparedStatement createStmt = conn.prepareStatement(createTableSQL)) {
                createStmt.executeUpdate();
            }

            // Insert the request
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                pstmt.setInt(1, userId);
                pstmt.setInt(2, petId);
                pstmt.setString(3, "Pending");
                return pstmt.executeUpdate() > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            mySql.closeConnection(conn);
        }
    }
    public List<Map<String, Object>> getAllRequestsWithUserAndPet() {
    List<Map<String, Object>> requests = new ArrayList<>();
    String sql = "SELECT ar.id AS request_id, u.name AS user_name, u.email, "
           + "p.name AS pet_name, p.breed AS pet_breed, "
           + "ar.status, ar.request_date "
           + "FROM adoption_requests ar "
           + "JOIN users u ON ar.user_id = u.id "
           + "JOIN pets p ON ar.pet_id = p.id";
    try (Connection conn = mySql.openConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
            Map<String, Object> req = new HashMap<>();
            req.put("request_id", rs.getInt("request_id"));
            req.put("user_name", rs.getString("user_name"));
            req.put("email", rs.getString("email"));
            // req.put("phone", rs.getString("phone")); // Uncomment when available
            req.put("pet_name", rs.getString("pet_name"));
            req.put("pet_breed", rs.getString("pet_breed"));
            req.put("status", rs.getString("status"));
            req.put("request_date", rs.getString("request_date"));
            requests.add(req);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return requests;
}
   // Update status
    public boolean updateRequestStatus(int requestId, String newStatus) {
        String sql = "UPDATE adoption_requests SET status = ? WHERE id = ?";
        try (Connection conn = mySql.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newStatus);
            pstmt.setInt(2, requestId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Delete request
    public boolean deleteRequest(int requestId) {
        String sql = "DELETE FROM adoption_requests WHERE id = ?";
        try (Connection conn = mySql.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, requestId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    } 

}
