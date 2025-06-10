/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petadoptiontracker.model;

/**
 *
 * @author LeathLOQ
 */
public class ListingData {
    private int id;
    private String name;
    private int age;
    private String species;
    private String breed;
    private  byte[] image;
    private String description;
    public ListingData(int id, String name,int age,String species, String breed, byte[] image,String description){
        this.id=id;
        this.name=name;
        this.age=age;
        this.species=species;
        this.breed=breed;
        this.image=image;
        this.description=description;
    }
    public void setId(int id){ 
        this.id=id;
    }
    public int getId(){
        return this.id;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public void setAge(int age){ 
        this.age=age;
    }
    public int getAge(){
        return this.age;
    }
    public void setSpecies(String species){
        this.species=species;
    }
    public String getSpecies(){
        return this.species;
    }
    public void setBreed(String breed){
        this.breed=breed;
    }
    public String getBreed(){
        return this.breed;
    }
    public void setImage(byte[] image){
        this.image=image;
    }
    public byte[] getImage(){
        return this.image;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public String getDescription(){
        return this.description;
    }
}
