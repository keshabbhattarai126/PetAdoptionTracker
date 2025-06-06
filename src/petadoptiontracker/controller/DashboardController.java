
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author keshab
 */
package petadoptiontracker.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import petadoptiontracker.view.DashboardView;

import petadoptiontracker.view.DashboardView;
import petadoptiontracker.view.EntryView;

/**
 *
 * @author keshab
 */

public class DashboardController {
    DashboardView dashboardView;
    public DashboardController(DashboardView dashboardView){
        this.dashboardView=dashboardView;
        this.dashboardView.addSignOutListener(new SignOutListener());
    }
    public void open(){
        this.dashboardView.setVisible(true);
    }
    public void close(){
        this.dashboardView.dispose();
    }
    
    
    class SignOutListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int confirm = JOptionPane.showConfirmDialog(
                    dashboardView,
                    "Are you sure you want to log out?",
                    "Logout Confirmation",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {            
            SessionManager.logout();            
            dashboardView.dispose();
            EntryView entryView = new EntryView();
            EntryController entryController = new EntryController(entryView);
            entryController.open();
            }
        }
        
    }
}