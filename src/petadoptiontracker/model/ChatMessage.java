package petadoptiontracker.model;

import java.sql.Timestamp;

public class ChatMessage {
    private int id;
    private int senderId;
    private int receiverId;
    private String message;
    private Timestamp timestamp;
    private boolean isFromAdmin;
    private boolean isRead;
    private String senderName;
    private String receiverName;
    
    // Constructors
    public ChatMessage() {}
    
    public ChatMessage(int senderId, int receiverId, String message, boolean isFromAdmin) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.isFromAdmin = isFromAdmin;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getSenderId() { return senderId; }
    public void setSenderId(int senderId) { this.senderId = senderId; }
    
    public int getReceiverId() { return receiverId; }
    public void setReceiverId(int receiverId) { this.receiverId = receiverId; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public Timestamp getTimestamp() { return timestamp; }
    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }
    
    public boolean isFromAdmin() { return isFromAdmin; }
    public void setFromAdmin(boolean fromAdmin) { isFromAdmin = fromAdmin; }
    
    public boolean isRead() { return isRead; }
    public void setRead(boolean read) { isRead = read; }
    
    public String getSenderName() { return senderName; }
    public void setSenderName(String senderName) { this.senderName = senderName; }
    
    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }
}
