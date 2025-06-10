package petadoptiontracker.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import petadoptiontracker.dao.AdminDao;
import petadoptiontracker.dao.UserDao;
import petadoptiontracker.model.PetModel;
import petadoptiontracker.model.UserData;
import petadoptiontracker.view.AdminDashboardView;
import petadoptiontracker.view.EntryView;

public class AdminDashboardController {
    private final AdminDashboardView adminDashboardView;
    private File selectedPetImage = null;

    public AdminDashboardController(AdminDashboardView view) {
        this.adminDashboardView = view;
        adminDashboardView.addSearchButtonListener(new SearchButtonListener());
        adminDashboardView.addSignOutButtonListener(new SignOutListener());
        adminDashboardView.addPetButtonListener(new AddPetListener());
        adminDashboardView.addPetPhotoUploadButtonListener(new UploadPhotoListener());
        
        // Add admin-specific listeners here as you build features
    }

    public void open() {
        adminDashboardView.setVisible(true);
    }

    public void close() {
        adminDashboardView.dispose();
    }
    
    class UploadPhotoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(adminDashboardView);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedPetImage = fileChooser.getSelectedFile();
//                adminDashboardView.setPhotoPreview(selectedPetImage.getAbsolutePath());
            }
        }
    }
    
    class AddPetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Get form values
                String name = adminDashboardView.getPetName().getText();
                String breed = adminDashboardView.getPetBreed().getText();
                String ageText = adminDashboardView.getPetAge().getText();
                String sex = adminDashboardView.getSelectedPetSex();
                String status = adminDashboardView.getSelectedPetStatus();

                // Validation
                if (name.isEmpty() || breed.isEmpty() || ageText.isEmpty() ||
                    sex.isEmpty() || selectedPetImage == null) {
                    JOptionPane.showMessageDialog(adminDashboardView,
                        "Please fill all required fields and select a photo",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int age;
                try {
                    age = Integer.parseInt(ageText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(adminDashboardView,
                        "Please enter a valid age number",
                        "Invalid Age",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Read image file
                byte[] imageBytes = Files.readAllBytes(selectedPetImage.toPath());

                // Create PetModel
                PetModel newPet = new PetModel();
                newPet.setName(name);
                newPet.setBreed(breed);
                newPet.setAge(age);
                newPet.setSex(sex);
                newPet.setPhoto(imageBytes);
                newPet.setStatus(status.isEmpty() ? "Available" : status);

                // Save to database
                AdminDao adminDao = new AdminDao();
                boolean success = adminDao.addPet(newPet);

                if (success) {
                    JOptionPane.showMessageDialog(adminDashboardView,
                        "Pet added successfully!");
//                    clearPetForm();
                } else {
                    JOptionPane.showMessageDialog(adminDashboardView,
                        "Failed to add pet to database");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(adminDashboardView,
                    "Error reading image file: " + ex.getMessage(),
                    "File Error",
                    JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(adminDashboardView,
                    "Unexpected error: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
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
