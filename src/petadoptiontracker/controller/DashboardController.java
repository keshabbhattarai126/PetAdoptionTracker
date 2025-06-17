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
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import petadoptiontracker.dao.RequestDao;
import petadoptiontracker.dao.UserDao;
import petadoptiontracker.model.PetModel;
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
        dashboardView.viewPetTabButtonListener(new ViewPetTabListener());
        dashboardView.requestButtonListener(new RequestButtonListener());
    }

    public void open() {
        dashboardView.setVisible(true);
    }

    public void close() {
        dashboardView.dispose();
    }
     public void loadPetTable() {
    UserDao userDao = new UserDao();
    List<PetModel> petList = userDao.getAllPets();
    dashboardView.setTableData(petList);
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
    class ViewPetTabListener implements ActionListener{

      @Override
      public void actionPerformed(ActionEvent e) {
          dashboardView.getTabbedPane().setSelectedIndex(1);

          loadPetTable();
          
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
//    adminDashboardView.viewPetTabButtonListener(new ViewPetTabListener());
 
//class ViewPetTabListener implements ActionListener {
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        dashboardView.getTabbedPane().setSelectedIndex(2); // tab3 index
//         Load data into the table
//    }
//}
    class RequestButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = dashboardView.getPetTable();
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(dashboardView, "Please select a pet to request.");
            return;
        }

        // Assuming pet ID is in column 0
        int petId = (Integer) table.getModel().getValueAt(selectedRow, 0);

        // Get the current user (from session manager or controller)
        UserData currentUser = SessionManager.getCurrentUser();
        if (currentUser == null) {
            JOptionPane.showMessageDialog(dashboardView, "User not logged in.");
            return;
        }
        int userId = currentUser.getId();

        // Save the request
        RequestDao requestDao = new RequestDao();
        boolean success = requestDao.createRequest(userId, petId);

        if (success) {
            JOptionPane.showMessageDialog(dashboardView, "Request submitted!");
        } else {
            JOptionPane.showMessageDialog(dashboardView, "Failed to submit request.");
        }
    }
}
}
//jgnsfjglskgpdoahjpeh