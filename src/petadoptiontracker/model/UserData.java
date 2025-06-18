/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petadoptiontracker.model;
import java.io.File;

/**
 *
 * @author keshab
 */
public class UserData {
    private int id;
    private String name;
    private String email;
    private String password;
    private  byte[] image;
    private String role;
    private String gender;
    private String phone;
    private String preference;
    public UserData(String name,String email, String password, byte[] image, String role){
        this.name= name;
        this.email=email;
        this.password=password;
        this.image=image;
        this.role=role;
    }
    public UserData(int id,String name,String email, String password, byte[] image, String role){
        this.id=id;
        this.name= name;
        this.email=email;
        this.password=password;
        this.image=image;
        this.role=role;
    }
    public UserData(){
        
    }
    public void setId(int id){ //setters
        this.id=id;
    }
    public int getId(){ //getters
        return this.id;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return this.password;
    }
    public void setImage(byte[] image){
        this.image=image;
    }
    public byte[] getImage(){
        return this.image;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return the preference
     */
    public String getPreference() {
        return preference;
    }
    public void setGender(String gender) {
    this.gender = gender;
}

public void setPhone(String phone) {
    this.phone = phone;
}

public void setPreference(String preference) {
    this.preference = preference;
}
}
