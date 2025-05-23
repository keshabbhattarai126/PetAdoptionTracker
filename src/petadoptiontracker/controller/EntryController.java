/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petadoptiontracker.controller;

import java.awt.event.ActionEvent;
import petadoptiontracker.view.EntryView;
import java.awt.event.ActionListener;
import petadoptiontracker.view.RegistrationView;

/**
 *
 * @author LeathLOQ
 */
public class EntryController {
    private EntryView entryView=new EntryView();
    public EntryController (EntryView entryView){
        this.entryView=entryView;
        entryView.addRegistrationNavigation(new RegistrationNavigation());
    }
    
    public void open(){
        this.entryView.setVisible(true);
    }
    public void close(){
        this.entryView.dispose();
    }
    
    
    class RegistrationNavigation implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        RegistrationView register= new RegistrationView();
        RegistrationController registrationController= new RegistrationController(register);
        registrationController.open();
        close();
    }
}
}
