package petadoptiontracker.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import petadoptiontracker.controller.SessionManager;

import petadoptiontracker.database.MySqlConnection;
import petadoptiontracker.model.ChatMessage;

public class ChatDao {
     MySqlConnection mySql = new MySqlConnection();
    
    public boolean sendMessage(int senderId, int receiverId, String message, boolean isFromAdmin) {
        String sql = "INSERT INTO chat_messages (sender_id, receiver_id, message, timestamp, is_from_admin, is_read) VALUES (?, ?, ?, NOW(), ?, false)";
        createChatTable();
        
        try (Connection conn = mySql.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, senderId);
            pstmt.setInt(2, receiverId);
            pstmt.setString(3, message);
            pstmt.setBoolean(4, isFromAdmin);
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<ChatMessage> getChatHistory(int userId) {
        createChatTable();
        String sql = "SELECT cm.*, u1.name as sender_name, u2.name as receiver_name " +
                    "FROM chat_messages cm " +
                    "JOIN users u1 ON cm.sender_id = u1.id " +
                    "JOIN users u2 ON cm.receiver_id = u2.id " +
                    "WHERE cm.sender_id = ? OR cm.receiver_id = ? " +
                    "ORDER BY cm.timestamp ASC";
        
        List<ChatMessage> messages = new ArrayList<>();
        
        try (Connection conn = mySql.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            pstmt.setInt(2, userId);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ChatMessage message = new ChatMessage();
                message.setId(rs.getInt("id"));
                message.setSenderId(rs.getInt("sender_id"));
                message.setReceiverId(rs.getInt("receiver_id"));
                message.setMessage(rs.getString("message"));
                message.setTimestamp(rs.getTimestamp("timestamp"));
                message.setFromAdmin(rs.getBoolean("is_from_admin"));
                message.setRead(rs.getBoolean("is_read"));
                message.setSenderName(rs.getString("sender_name"));
                message.setReceiverName(rs.getString("receiver_name"));
                messages.add(message);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return messages;
    }
    
  public List<Map<String, Object>> getUsersWithChatHistory() {
    createChatTable();
    String sql = "SELECT DISTINCT u.id, u.name, u.email, " +
                "MAX(cm.timestamp) as last_message_time, " +
                "COUNT(CASE WHEN cm.is_read = false AND cm.receiver_id = ? THEN 1 END) as unread_count " +
                "FROM users u " +
                "JOIN chat_messages cm ON (u.id = cm.sender_id OR u.id = cm.receiver_id) " +
                "WHERE u.role = 'User' AND (cm.sender_id = u.id OR cm.receiver_id = u.id) " +
                "GROUP BY u.id, u.name, u.email " +
                "ORDER BY last_message_time DESC";
    
    List<Map<String, Object>> usersWithChat = new ArrayList<>();
    try (Connection conn = mySql.openConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        // Get current admin ID
        int adminId = SessionManager.getCurrentUserId();
        pstmt.setInt(1, adminId);
        
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("id", rs.getInt("id"));
            userInfo.put("name", rs.getString("name"));
            userInfo.put("email", rs.getString("email"));
            userInfo.put("lastMessageTime", rs.getTimestamp("last_message_time"));
            userInfo.put("unreadCount", rs.getInt("unread_count"));
            usersWithChat.add(userInfo);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return usersWithChat;
}

    
    public boolean markMessagesAsRead(int userId, int adminId) {
        String sql = "UPDATE chat_messages SET is_read = true " +
                    "WHERE sender_id = ? AND receiver_id = ? AND is_read = false";
        
        try (Connection conn = mySql.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            pstmt.setInt(2, adminId);
            
            return pstmt.executeUpdate() >= 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void createChatTable() {
    String sql = "CREATE TABLE IF NOT EXISTS chat_messages ("
            + "id INT PRIMARY KEY AUTO_INCREMENT,"
            + "sender_id INT NOT NULL,"
            + "receiver_id INT NOT NULL,"
            + "message TEXT NOT NULL,"
            + "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
            + "is_from_admin BOOLEAN DEFAULT FALSE,"
            + "is_read BOOLEAN DEFAULT FALSE,"
            + "FOREIGN KEY (sender_id) REFERENCES users(id),"
            + "FOREIGN KEY (receiver_id) REFERENCES users(id)"
            + ")";
    try (Connection conn = mySql.openConnection();
         Statement stmt = conn.createStatement()) {
        stmt.executeUpdate(sql);
        System.out.println("chat_messages table created or already exists.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
