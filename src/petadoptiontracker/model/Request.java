/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petadoptiontracker.model;

/**
 *
 * @author LeathLOQ
 */
public class Request {

    public Request(int userId1, int petId1) {
    }
    private int requestId;
    private int userId;
    private int petId;
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
    public int getRequestId() {
        return requestId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getUserId() {
        return userId;
    }    
    public void setPetId(int petId) {
        this.petId = petId;
    }
    public int getPetId() {
        return petId;
    }
}
