/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petadoptiontracker.controller;

import java.awt.event.ActionEvent;
import petadoptiontracker.view.EntryView;
import java.awt.event.ActionListener;
import petadoptiontracker.view.LoginView;
import petadoptiontracker.view.RegistrationView;

/**
 *
 * @author LeathLOQ
 */
public class EntryController {
    private final EntryView entryView;
    public EntryController(EntryView entryView){
        
        System.err.flush();
        this.entryView=entryView;
        entryView.addLoginListener(new LoginListener());
        entryView.addRegisterListener(new RegistrationListener());
        
    }
    public void open(){
        this.entryView.setVisible(true);
    }
    public void close(){
        this.entryView.dispose();
    }
    class LoginListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            LoginView loginView= new LoginView();
            LoginController loginController= new LoginController(loginView);
            loginController.open();
            close();
        }
        
    }
    class RegistrationListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            RegistrationView registrationView= new RegistrationView();
            RegistrationController registrationController= new RegistrationController(registrationView);
            registrationController.open();
            close();
        }
        
    }
    
}

