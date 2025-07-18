/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petadoptiontracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import petadoptiontracker.database.MySqlConnection;
import petadoptiontracker.model.LoginRequest;
import petadoptiontracker.model.PetModel;
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
         String createTableSQL = "CREATE TABLE IF NOT EXISTS Users ("
                 + "id INT AUTO_INCREMENT PRIMARY KEY, "
                 + "name VARCHAR(50) NOT NULL, "
                 + "email VARCHAR(100) UNIQUE NOT NULL, "
                 + "password VARCHAR(255) NOT NULL, "
                 + "image BLOB NOT NULL, "
                 + "role VARCHAR(10) NOT NULL DEFAULT 'USER', "
                 + "gender VARCHAR(20), "
                 + "phone VARCHAR(20), "
                 + "preference TEXT"
                 + ")";
         String query=  "INSERT INTO Users (name, email, password,image,role) VALUES (?, ?, ?,?,?)";
         
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
        String sql = "SELECT * FROM Users where email = ? and password = ?";
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
            String query="SELECT * FROM Users where email=?";
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
        String query = "UPDATE Users SET password=? WHERE email=?";
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
        String sql = "SELECT * FROM Users WHERE name = ?";

        try (Connection conn = mySql.openConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                UserData user = new UserData();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setImage(rs.getBytes("image"));
                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
      
        

//   import java.util.ArrayList;

public List<PetModel> getAllPets() {
    List<PetModel> petList = new ArrayList<>();
    String sql = "SELECT * FROM Pets"; // Adjust table name and columns if needed
    Connection conn = mySql.openConnection();

    try (PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            PetModel pet = new PetModel();
            pet.setId(rs.getInt("id")); // Adjust column names as per your schema
            pet.setName(rs.getString("name"));
            pet.setBreed(rs.getString("breed"));
            pet.setAge(rs.getInt("age"));
            pet.setSex(rs.getString("sex"));
            pet.setStatus(rs.getString("status"));
            petList.add(pet);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        mySql.closeConnection(conn);
    }

    return petList;
}
    public boolean updateUserProfile(UserData userData) {
    String sql = "UPDATE Users SET gender=?, phone=?, preference=? WHERE id=?";
    Connection conn = mySql.openConnection();
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, userData.getGender());
        pstmt.setString(2, userData.getPhone());
        pstmt.setString(3, userData.getPreference());
        pstmt.setInt(4, userData.getId());
        int result = pstmt.executeUpdate();
        return result > 0;
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        mySql.closeConnection(conn);
    }
    return false;
}

public List<PetModel> searchPets(String query) {
    List<PetModel> pets = new ArrayList<>();
    String sql = "SELECT * FROM pets WHERE name LIKE ? OR breed LIKE ?";
    try (Connection conn = mySql.openConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, "%" + query + "%");
        pstmt.setString(2, "%" + query + "%");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            PetModel pet = new PetModel();
            pet.setId(rs.getInt("id"));
            pet.setName(rs.getString("name"));
            pet.setBreed(rs.getString("breed"));
            pet.setAge(rs.getInt("age"));
            pet.setSex(rs.getString("sex"));
            pet.setStatus(rs.getString("status"));
            pets.add(pet);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return pets;
}



}
