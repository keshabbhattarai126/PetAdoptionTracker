/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petadoptiontracker.dao;

import petadoptiontracker.database.MySqlConnection;
import petadoptiontracker.model.Review;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author LeathLOQ
 */
public class ReviewDao {
     MySqlConnection mySql = new MySqlConnection();

    // Create the table if it doesn't exist
    public ReviewDao() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Reviews ("
            + "id INT AUTO_INCREMENT PRIMARY KEY, "
            + "review_text TEXT NOT NULL, "
            + "rating INT NOT NULL"
            + ")";
        try (Connection conn = mySql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(createTableSQL)) {
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean addReview(Review review) {
        String sql = "INSERT INTO Reviews (review_text, rating) VALUES (?, ?)";
        Connection conn = mySql.openConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, review.getReviewText());
            pstmt.setInt(2, review.getRating());
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            mySql.closeConnection(conn);
        }
        return false;
    }
}
