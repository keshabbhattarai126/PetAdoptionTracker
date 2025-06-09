/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petadoptiontracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import petadoptiontracker.database.MySqlConnection;
import petadoptiontracker.model.UserData;

public class AdminDao {
    MySqlConnection mySql = new MySqlConnection();

    // Fetch admin by email
    public UserData getAdminByEmail(String email) {
        String sql = "SELECT * FROM demoUsers WHERE email = ? AND role = 'ADMIN'";
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
}
