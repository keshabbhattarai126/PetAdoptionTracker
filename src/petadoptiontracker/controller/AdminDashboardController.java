package petadoptiontracker.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import petadoptiontracker.dao.UserDao;
import petadoptiontracker.model.UserData;
import petadoptiontracker.view.AdminDashboardView;
import petadoptiontracker.view.EntryView;

public class AdminDashboardController {
    private final AdminDashboardView adminDashboardView;

    public AdminDashboardController(AdminDashboardView view) {
        this.adminDashboardView = view;
        adminDashboardView.addSearchButtonListener(new SearchButtonListener());
        adminDashboardView.addSignOutButtonListener(new SignOutListener());
        // Add admin-specific listeners here as you build features
    }

    public void open() {
        adminDashboardView.setVisible(true);
    }

    public void close() {
        adminDashboardView.dispose();
    }
    class SignOutListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int confirm = JOptionPane.showConfirmDialog(
                    adminDashboardView,
                    "Are you sure you want to log out?",
                    "Logout Confirmation",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {            
            SessionManager.logout();            
            adminDashboardView.dispose();
            EntryView entryView = new EntryView();
            EntryController entryController = new EntryController(entryView);
            entryController.open();
            }
        }
        
    }
    
    

    class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nameInput = adminDashboardView.getSearchInput().trim();

            if (nameInput.isEmpty()) {
                JOptionPane.showMessageDialog(adminDashboardView, "Please enter a name to search.");
                return;
            }

            UserDao dao = new UserDao();
            UserData user = dao.getUserByName(nameInput);

            if (user != null) {
                adminDashboardView.setSearchResult(user.getName(), user.getEmail());
            } else {
                JOptionPane.showMessageDialog(adminDashboardView, "User not found.");
                adminDashboardView.clearSearchResult();
            }
        }
    }
}
