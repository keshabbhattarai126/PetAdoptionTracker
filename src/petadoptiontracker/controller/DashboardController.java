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
import petadoptiontracker.dao.AdminDao;
import petadoptiontracker.dao.FavoritesDao;
import petadoptiontracker.dao.RequestDao;
import petadoptiontracker.dao.UserDao;
import petadoptiontracker.model.PetModel;
import petadoptiontracker.model.UserData;
import petadoptiontracker.view.DashboardView;
import petadoptiontracker.view.EntryView;
import petadoptiontracker.view.PetProfileView;




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
        dashboardView.addFavoriteButtonListener(new FavoriteButtonListener());
        dashboardView.addHeartButtonListener(new HeartButtonListener());
        dashboardView.addDashboardTabButtonListener(new DashboardTabListener());
        dashboardView.addProfileTabButtonListener(new ProfileTabListener());
        dashboardView.addViewPetProfileListener(new ViewPetProfileListener()); //ViewPetProfileOperation
        


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
    class FavoriteButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = dashboardView.getPetTable();
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(dashboardView, "Please select a pet to favorite.");
            return;
        }
        int petId = (Integer) table.getModel().getValueAt(selectedRow, 0);
        UserData currentUser = SessionManager.getCurrentUser();
        if (currentUser == null) {
            JOptionPane.showMessageDialog(dashboardView, "User not logged in.");
            return;
        }
        FavoritesDao favoritesDao = new FavoritesDao();
        boolean success = favoritesDao.addToFavorites(currentUser.getId(), petId);
        if (success) {
            JOptionPane.showMessageDialog(dashboardView, "Pet added to favorites!");
        } else {
            JOptionPane.showMessageDialog(dashboardView, "Already in favorites or failed.");
        }
    }
}

class HeartButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        UserData currentUser = SessionManager.getCurrentUser();
        if (currentUser == null) {
            JOptionPane.showMessageDialog(dashboardView, "User not logged in.");
            return;
        }
        FavoritesDao favoritesDao = new FavoritesDao();
        List<PetModel> favorites = favoritesDao.getFavoritesByUser(currentUser.getId());
        // Show favorites in a simple dialog for now
        StringBuilder sb = new StringBuilder();
        for (PetModel pet : favorites) {
            sb.append(pet.getName())
              .append(" (").append(pet.getBreed()).append(") ")
              .append("- Age: ").append(pet.getAge())
              .append(", Sex: ").append(pet.getSex())
              .append("\n");

        }
        JOptionPane.showMessageDialog(dashboardView, sb.length() > 0 ? sb.toString() : "No favorites yet.");
    }
}
class DashboardTabListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        dashboardView.getTabbedPane().setSelectedIndex(0); // Dashboard tab
    }
}
class ProfileTabListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        dashboardView.getTabbedPane().setSelectedIndex(2); // Profile Setting tab
    }
}

class ViewPetProfileListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = dashboardView.getPetTable(); // getPetTable() returns your JTable
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(dashboardView, "Please select a pet to view.");
            return;
        }
        // Assuming pet ID is in column 0
        int petId = (Integer) table.getModel().getValueAt(selectedRow, 0);

        // Fetch pet details from DAO
        AdminDao adminDao = new AdminDao();
        PetModel pet = adminDao.getPetById(petId);

        if (pet != null) {
            PetProfileView profileView = new PetProfileView(pet);
            profileView.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(dashboardView, "Could not load pet details.");
        }
    }
}
class SearchButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // 1. Switch to tab4 (index 3, as tab indices start at 0)
        dashboardView.getTabbedPane().setSelectedIndex(3);

        // 2. Get the search input
        String query = dashboardView.getSearchInput();

        // 3. Fetch search results (example: by name or breed)
        UserDao userDao = new UserDao();
        List<PetModel> searchResults = userDao.searchPets(query);

        // 4. Populate the searchResultTable
        dashboardView.setSearchResultTableData(searchResults);
    }
}


}
