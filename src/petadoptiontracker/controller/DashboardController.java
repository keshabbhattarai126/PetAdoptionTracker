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
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import petadoptiontracker.dao.AdminDao;
import petadoptiontracker.dao.ChatDao;
import petadoptiontracker.dao.FavoritesDao;
import petadoptiontracker.dao.RequestDao;
import petadoptiontracker.dao.ReviewDao;
import petadoptiontracker.dao.UserDao;
import petadoptiontracker.model.ChatMessage;
import petadoptiontracker.model.PetModel;
import petadoptiontracker.model.Review;
import petadoptiontracker.model.UserData;
import petadoptiontracker.view.DashboardView;
import petadoptiontracker.view.EntryView;
import petadoptiontracker.view.PetProfileView;
import petadoptiontracker.view.ReviewView;




/**
 *
 * @author keshab
 */

public class DashboardController {
    private final DashboardView dashboardView;
    private ChatDao chatDao;


    public DashboardController(DashboardView dashboardView) {
        this.dashboardView = dashboardView;
        this.chatDao = new ChatDao();

        // Register button listeners
//        dashboardView.addMyRequestButtonListener(new MyRequestListener());
        dashboardView.addSearchButtonListener(new SearchButtonListener());
        dashboardView.addSignOutButtonListener(new SignOutListener());
        dashboardView.viewPetTabButtonListener(new ViewPetTabListener());
//        dashboardView.requestButtonListener(new RequestButtonListener());
        dashboardView.addFavoriteButtonListener(new FavoriteButtonListener());
        dashboardView.addHeartButtonListener(new HeartButtonListener());
        dashboardView.addDashboardTabButtonListener(new DashboardTabListener());
//        dashboardView.addProfileTabButtonListener(new ProfileTabListener());
//        dashboardView.addViewPetProfileListener(new ViewPetProfileListener()); //ViewPetProfileOperation

        dashboardView.addProfileSubmitListener(new ProfileSubmitListener());
        dashboardView.addSendMessageButtonListener(new SendMessageListener());
        dashboardView.addMessageTabListener(new MessageTabListener());
        dashboardView.addReviewButtonListener(new ReviewListener());
        dashboardView.addBrowsePetButtonListener(new BrowsePetListener());
        dashboardView.addProfileButtonListener(new ProfileButtonListener());
        dashboardView.requestButtonListener(e -> handlePetRequest(dashboardView.getPetTable()));
        dashboardView.addViewPetProfileListener(e -> handleViewPetProfile(dashboardView.getPetTable()));
        dashboardView.addRequestButton1Listener(e -> handlePetRequest(dashboardView.getSearchResultTable()));
        dashboardView.addViewPetProfileButton1Listener(e -> handleViewPetProfile(dashboardView.getSearchResultTable()));


         loadPetTable();



    }

    public void open() {
        dashboardView.setVisible(true);
        loadRequestStatusTable();
    }

    public void close() {
        dashboardView.dispose();
    }
    
    // Generalized request logic
private void handlePetRequest(JTable table) {
    int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(dashboardView, "Please select a pet to request.");
        return;
    }
    int petId = (Integer) table.getModel().getValueAt(selectedRow, 0);
    UserData currentUser = SessionManager.getCurrentUser();
    if (currentUser == null) {
        JOptionPane.showMessageDialog(dashboardView, "User not logged in.");
        return;
    }
    int userId = currentUser.getId();
    RequestDao requestDao = new RequestDao();
    boolean success = requestDao.createRequest(userId, petId);
    if (success) {
        JOptionPane.showMessageDialog(dashboardView, "Request submitted!");
        refreshRequestStatusTable();
    } else {
        JOptionPane.showMessageDialog(dashboardView, "Failed to submit request.");
    }
}

//METHOD: Refresh table data
    public void refreshRequestStatusTable() {
        UserData currentUser = SessionManager.getCurrentUser();
        if (currentUser != null) {
            RequestDao requestDao = new RequestDao();
            List<Map<String, Object>> requests = requestDao.getRequestsByUser(currentUser.getId());
            dashboardView.setRequestStatusTableData(requests);
        }
    }

// Generalized view profile logic
private void handleViewPetProfile(JTable table) {
    int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(dashboardView, "Please select a pet to view.");
        return;
    }
    int petId = (Integer) table.getModel().getValueAt(selectedRow, 0);
    AdminDao adminDao = new AdminDao();
    PetModel pet = adminDao.getPetById(petId);
    if (pet != null) {
        PetProfileView profileView = new PetProfileView(pet);
        profileView.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(dashboardView, "Could not load pet details.");
    }
}

    
    public void loadUserChatHistory() {
    UserData currentUser = SessionManager.getCurrentUser();
    if (currentUser != null) {
        List<ChatMessage> messages = chatDao.getChatHistory(currentUser.getId());
        dashboardView.displayChatHistory(messages);
        
        // Mark admin messages as read
        chatDao.markMessagesAsRead(1, currentUser.getId()); // Assuming admin ID is 1
    }
}
    
    private void loadRequestStatusTable() {
    UserData currentUser = SessionManager.getCurrentUser();
    if (currentUser != null) {
        RequestDao requestDao = new RequestDao();
        List<Map<String, Object>> requests = requestDao.getRequestsByUser(currentUser.getId());
        dashboardView.setRequestStatusTableData(requests);
    }
}
    
    // Listener classes for user dashboard
class SendMessageListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        UserData currentUser = SessionManager.getCurrentUser();
        if (currentUser == null) {
            JOptionPane.showMessageDialog(dashboardView, 
                "Please log in to send messages.");
            return;
        }
        
        String message = dashboardView.getMessageInput().trim();
        if (message.isEmpty()) {
            JOptionPane.showMessageDialog(dashboardView, 
                "Please enter a message.");
            return;
        }
        
        // Send message to admin (assuming admin ID is 1)
        boolean success = chatDao.sendMessage(currentUser.getId(), 1, message, false);
        
       if (success) {
      dashboardView.clearMessageInput();
    loadUserChatHistory(); // Refresh chat history
    JOptionPane.showMessageDialog(  dashboardView, "Message sent successfully!");
} else {
    JOptionPane.showMessageDialog(  dashboardView, "Failed to send message.");
}

    }
}

class MessageTabListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        dashboardView.getTabbedPane().setSelectedIndex(3); 
        loadUserChatHistory(); // Load chat history when message tab is opened
    }
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
        dashboardView.getTabbedPane().setSelectedIndex(1); // Profile Setting tab
    }
}

class SearchButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // 1. Switch to tab4 (index 3, as tab indices start at 0)
        dashboardView.getTabbedPane().setSelectedIndex(4);

        // 2. Get the search input
        String query = dashboardView.getSearchInput();

        // 3. Fetch search results (example: by name or breed)
        UserDao userDao = new UserDao();
        List<PetModel> searchResults = userDao.searchPets(query);

        // 4. Populate the searchResultTable
        dashboardView.setSearchResultTableData(searchResults);
    }
}


    class ProfileSubmitListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the current user (from session or controller)
        UserData currentUser = SessionManager.getCurrentUser();
        if (currentUser == null) {
            JOptionPane.showMessageDialog(dashboardView, "User not logged in.");
            return;
        }

        // Read values from the view
        String gender = dashboardView.getProfileGender();
        String phone = dashboardView.getProfilePhone();
        String preference = dashboardView.getProfilePreference();

        // Update the UserData object
        currentUser.setGender(gender);
        currentUser.setPhone(phone);
        currentUser.setPreference(preference);

        // Save to database (you need to implement updateUserProfile in UserDao)
        UserDao userDao = new UserDao();
        boolean success = userDao.updateUserProfile(currentUser);

        if (success) {
            JOptionPane.showMessageDialog(dashboardView, "Profile updated successfully!");
        } else {
            JOptionPane.showMessageDialog(dashboardView, "Failed to update profile.");
        }
    }
    }
    class ReviewListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ReviewView reviewView = new ReviewView();
        reviewView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        reviewView.setVisible(true);

        // Register the submit button listener for this instance
        reviewView.addSubmitButtonListener(new ReviewSubmitButtonListener(reviewView));
    }
}

    class ReviewSubmitButtonListener implements ActionListener {
    private final ReviewView reviewView;

    public ReviewSubmitButtonListener(ReviewView reviewView) {
        this.reviewView = reviewView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String reviewText = reviewView.getReviewText();
        int rating = reviewView.getRating();
        if (reviewText.trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(reviewView, "Please enter a review.");
            return;
        }

        Review review = new Review(reviewText, rating);
        ReviewDao reviewDao = new ReviewDao();
        boolean success = reviewDao.addReview(review);

        if (success) {
            javax.swing.JOptionPane.showMessageDialog(reviewView, "Review submitted!");
        } else {
            javax.swing.JOptionPane.showMessageDialog(reviewView, "Error saving review.");
        }
    }
}
    class BrowsePetListener implements ActionListener{

      @Override
      public void actionPerformed(ActionEvent e) {
          dashboardView.getTabbedPane().setSelectedIndex(1);

          loadPetTable();
          
        }
    
    }
    
    class ProfileButtonListener implements ActionListener{

      @Override
      public void actionPerformed(ActionEvent e) {
          dashboardView.getTabbedPane().setSelectedIndex(2);
          
        }
    
    }
    
    

}

