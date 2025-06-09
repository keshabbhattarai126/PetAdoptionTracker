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
import petadoptiontracker.model.LoginRequest;
import petadoptiontracker.model.ResetPasswordRequest;
import petadoptiontracker.model.UserData;

/**
 *
 * @author keshab
 */
public class UserDao {
    MySqlConnection mySql = new MySqlConnection();
    public boolean registerUser(UserData userData){
        Connection conn= mySql.openConnection();
         String createTableSQL = "CREATE TABLE IF NOT EXISTS demoUsers ("
            + "id INT AUTO_INCREMENT PRIMARY KEY, "               
            + "name VARCHAR(50) NOT NULL, "
            + "email VARCHAR(100) UNIQUE NOT NULL, "
            + "password VARCHAR(255) NOT NULL, "
            + "image BLOB NOT NULL,"
            + "role VARCHAR(10) NOT NULL DEFAULT 'USER'" 
            + ")";
         String query=  "INSERT INTO demoUsers (name, email, password,image,role) VALUES (?, ?, ?,?,?)";
         
        try {
            PreparedStatement createtbl= conn.prepareStatement(createTableSQL);
            createtbl.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(UserDao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userData.getName());
            pstmt.setString(2, userData.getEmail());
            pstmt.setString(3, userData.getPassword());
            pstmt.setBytes(4, userData.getImage());
            pstmt.setString(5, userData.getRole() != null ? userData.getRole() : "USER");
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            System.err.println(ex);

        } finally {
            mySql.closeConnection(conn);
        }
          return false;
    }
    
    public UserData loginUser(LoginRequest loginData){
        Connection conn = mySql.openConnection();
        String sql = "SELECT * FROM demoUsers where email = ? and password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, loginData.getEmail());
            pstmt.setString(2, loginData.getPassword());
            ResultSet result = pstmt.executeQuery();
            if(result.next()){
                UserData user  = new UserData(
                    result.getString("name"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getBytes("image"),
                        result.getString("role")
                );
                user.setId(result.getInt("id"));
                
                return user;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            mySql.closeConnection(conn);
        }
        return null;
    }
        public boolean checkEmail(String email){
            String query="SELECT * FROM demoUsers where email=?";
            Connection conn= mySql.openConnection();
            try{
              PreparedStatement stmnt = conn.prepareStatement(query);
              stmnt.setString(1,email);
              ResultSet result = stmnt.executeQuery();
              if(result.next()){
                return true;
            }else{
                return false;
            }
            }catch(Exception e){
                return false;              
            }finally{
                mySql.closeConnection(conn);
                
            }
        
    }
        public boolean resetPassword(ResetPasswordRequest resetReq){
        String query = "UPDATE demoUsers SET password=? WHERE email=?";
        Connection conn=mySql.openConnection();
        try{
            PreparedStatement stmnt= conn.prepareStatement(query);
            stmnt.setString(1,resetReq.getPassword());
            stmnt.setString(2,resetReq.getEmail());
            int result = stmnt.executeUpdate();
            return result>0;
        }catch(Exception e){
            return false;
        }finally{
            mySql.closeConnection(conn);
        }
    }
        
        public UserData getUserByName(String name) {
        String sql = "SELECT * FROM demoUsers WHERE name = ?";

        try (Connection conn = mySql.openConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                UserData user = new UserData();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setImage(rs.getBytes("image")); // If you store user image as BLOB
                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // User not found
    }

}
