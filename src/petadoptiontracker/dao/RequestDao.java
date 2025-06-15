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
}
