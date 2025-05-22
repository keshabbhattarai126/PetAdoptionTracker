/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petadoptiontracker.controller;
import petadoptiontracker.view.RegistrationView;
/**
 *
 * @author LeathLOQ
 */
public class RegistrationController {
    private RegistrationView registrationView=new RegistrationView();
    public RegistrationController (RegistrationView registrationView){
    this.registrationView=registrationView;
}
    public void open(){
        this.registrationView.setVisible(true);
    }
    public void close(){
        this.registrationView.dispose();
    }
}
