/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petadoptiontracker.model;

/**
 *
 * @author Kamal
 */
public class ResetPasswordRequest {
    private String email;
    private String password;
    public ResetPasswordRequest (String email,String password){
        this.email=email;
        this.password=password;
    }
    public void setEmail (String email){
        this.email=email;
    }
    public void setPassword (String password){
        this.password=password;
    }
    public String getEnail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
}
