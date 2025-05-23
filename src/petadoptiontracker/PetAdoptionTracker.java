/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package petadoptiontracker;
import petadoptiontracker.controller.EntryController;
import petadoptiontracker.controller.RegistrationController;
import petadoptiontracker.view.EntryView;
import petadoptiontracker.view.RegistrationView;
/**
 *
 * @author keshab
 */
public class PetAdoptionTracker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntryView entryView=new EntryView();
        EntryController entryController=new EntryController(entryView);
        entryController.open();
        
//        RegistrationView registrationView=new RegistrationView();
//        RegistrationController registrationController=new RegistrationController(registrationView);
//        registrationController.open();
    }
}
