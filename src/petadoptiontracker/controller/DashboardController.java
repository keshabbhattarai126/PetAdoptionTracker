
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
import petadoptiontracker.dao.UserDao;
import petadoptiontracker.model.UserData;
import petadoptiontracker.view.DashboardView;
import petadoptiontracker.view.EntryView;
//import petadoptiontracker.view.MyRequestView;

/**
 *
 * @author keshab
 */
public class DashboardController {
    private final DashboardView dashboardView;

    public DashboardController(DashboardView dashboardView) {
        this.dashboardView = dashboardView;

        // Register button listeners
//        dashboardView.addMyRequestButtonListener(new MyRequestListener());
        dashboardView.addSearchButtonListener(new SearchButtonListener());
        dashboardView.addSignOutButtonListener(new SignOutListener());
    }

    public void open() {
        dashboardView.setVisible(true);
    }

    public void close() {
        dashboardView.dispose();
    }

//    class MyRequestListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            MyRequestView myRequestView = new MyRequestView();
//            MyRequestController myRequestController = new MyRequestController(myRequestView);
//            myRequestController.open();
//            close(); 
//        }
//    }
    
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
    
    

    class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nameInput = dashboardView.getSearchInput().trim();

            if (nameInput.isEmpty()) {
                JOptionPane.showMessageDialog(dashboardView, "Please enter a name to search.");
                return;
            }

            UserDao dao = new UserDao();
            UserData user = dao.getUserByName(nameInput);

            if (user != null) {
                dashboardView.setSearchResult(user.getName(), user.getEmail());
            } else {
                JOptionPane.showMessageDialog(dashboardView, "User not found.");
                dashboardView.clearSearchResult();
            }
        }
    }
}