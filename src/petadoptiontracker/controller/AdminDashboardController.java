package petadoptiontracker.controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import petadoptiontracker.dao.AdminDao;
import petadoptiontracker.dao.RequestDao;
import petadoptiontracker.dao.UserDao;
import petadoptiontracker.model.PetModel;
import petadoptiontracker.model.UserData;
import petadoptiontracker.view.AdminDashboardView;
import petadoptiontracker.view.EntryView;
import petadoptiontracker.view.NotificationView;
import petadoptiontracker.view.PetProfileView;

public class AdminDashboardController {
    private final AdminDashboardView adminDashboardView;
    private File selectedPetImage = null;
    private File selectedPetImage2 = null;
    private File selectedPetImage3 = null;

    public AdminDashboardController(AdminDashboardView view) {
        this.adminDashboardView = view;
        adminDashboardView.addSearchButtonListener(new SearchButtonListener());
        adminDashboardView.addSignOutButtonListener(new SignOutListener());
        adminDashboardView.addPetButtonListener(new AddPetListener());
        adminDashboardView.addPetPhotoUploadButtonListener(new UploadPhotoListener());
        adminDashboardView.addPetTabButtonListener(new AddPetTabListener());
        adminDashboardView.viewPetTabButtonListener(new ViewPetTabListener());
        adminDashboardView.addDeletePetEntryListener(new DeletePetEntryListener()); //Delete Operation
        adminDashboardView.addViewPetProfileListener(new ViewPetProfileListener()); //ViewPetProfileOperation
        adminDashboardView.petPhotoUpload2Listener(new UploadPhotoListener2());
        adminDashboardView.petPhotoUpload3Listener(new UploadPhotoListener3());
        adminDashboardView.addDashboardButtonListener(new DashboardButtonListener());
        adminDashboardView.addNotifcationButtonListener(new NotifcationButtonListener());
        adminDashboardView.addEditEntryButtonListener(new EditEntryButtonListener());
        adminDashboardView.addShareButtonListener(new ShareButtonListener());
        // Add admin-specific listeners here as you build features
    }

    public void open() {
        adminDashboardView.setVisible(true);
    }

    public void close() {
        adminDashboardView.dispose();
    }
     public void loadPetTable() {
    AdminDao adminDao = new AdminDao();
    List<PetModel> petList = adminDao.getAllPets();
    adminDashboardView.setPetTableData(petList);
     }
     
     public void loadRequestsTable() {
    RequestDao requestDao = new RequestDao();
    List<Map<String, Object>> requests = requestDao.getAllRequestsWithUserAndPet();
    adminDashboardView.setRequestsTableData(requests); // Implement this in your view
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
                byte[] imageBytes2 = Files.readAllBytes(selectedPetImage2.toPath());
                byte[] imageBytes3 = Files.readAllBytes(selectedPetImage3.toPath());

                // Create PetModel
                PetModel newPet = new PetModel();
                newPet.setName(name);
                newPet.setBreed(breed);
                newPet.setAge(age);
                newPet.setSex(sex);
                newPet.setPhoto(imageBytes);
                newPet.setPhoto2(imageBytes2);
                newPet.setPhoto3(imageBytes3);
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
    
    
    class AddPetTabListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            adminDashboardView.getTabbedPane().setSelectedIndex(2);
        }
    }
    
    class ViewPetTabListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            adminDashboardView.getTabbedPane().setSelectedIndex(1);
            loadPetTable();
        }
    }
    
    class DashboardButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        adminDashboardView.getTabbedPane().setSelectedIndex(0); // tab2 = dashboard
        loadRequestsTable();
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
    class DeletePetEntryListener implements java.awt.event.ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            javax.swing.JTable table = adminDashboardView.getPetTable();
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(adminDashboardView, "Please select a pet to delete.");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(
            adminDashboardView,
            "Are you sure you want to delete the selected pet?",
            "Delete Confirmation",
            JOptionPane.YES_NO_OPTION
            );
            
            if (confirm == JOptionPane.YES_OPTION) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int petId = (Integer) model.getValueAt(selectedRow, 0); // Assuming ID is in column 0
                // Delete from database
                petadoptiontracker.dao.AdminDao adminDao = new petadoptiontracker.dao.AdminDao();
                boolean deleted = adminDao.deletePet(petId);
                
                if (deleted) {
                    model.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(adminDashboardView, "Pet deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(adminDashboardView, "Failed to delete pet from database.");
                }
            }
        }
    }
    class ViewPetProfileListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = adminDashboardView.getPetTable(); // getPetTable() returns your JTable
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(adminDashboardView, "Please select a pet to view.");
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
            JOptionPane.showMessageDialog(adminDashboardView, "Could not load pet details.");
        }
    }
}
    class UploadPhotoListener2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(adminDashboardView);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedPetImage2 = fileChooser.getSelectedFile();
//                adminDashboardView.setPhotoPreview(selectedPetImage.getAbsolutePath());
            }
        }
    }
    class UploadPhotoListener3 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(adminDashboardView);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedPetImage3 = fileChooser.getSelectedFile();
//                adminDashboardView.setPhotoPreview(selectedPetImage.getAbsolutePath());
            }
        }
    }
    class NotifcationButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            NotificationView notificationView = new NotificationView();
            notificationView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            notificationView.setVisible(true);            
        }    
    }
    
    class EditEntryButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = adminDashboardView.getPetTable();
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(adminDashboardView, "Please select a pet to edit.");
            return;
        }

        // Get pet ID from the selected row (assumed in column 0)
        Object value = table.getModel().getValueAt(selectedRow, 0);
        int petId;
        try {
            petId = (value instanceof Integer) ? (Integer) value : Integer.parseInt(value.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(adminDashboardView, "Invalid pet ID.");
            return;
        }

        // Fetch current pet data
        AdminDao adminDao = new AdminDao();
        PetModel pet = adminDao.getPetById(petId);
        if (pet == null) {
            JOptionPane.showMessageDialog(adminDashboardView, "Could not load pet details.");
            return;
        }

        // Show a dialog to edit pet details (simplest: use JOptionPane for each field)
        String newName = JOptionPane.showInputDialog(adminDashboardView, "Edit Name:", pet.getName());
        if (newName == null) return; // Cancelled
        String newBreed = JOptionPane.showInputDialog(adminDashboardView, "Edit Breed:", pet.getBreed());
        if (newBreed == null) return;
        String newAgeStr = JOptionPane.showInputDialog(adminDashboardView, "Edit Age:", pet.getAge());
        if (newAgeStr == null) return;
        int newAge;
        try {
            newAge = Integer.parseInt(newAgeStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(adminDashboardView, "Invalid age.");
            return;
        }
        String newSex = JOptionPane.showInputDialog(adminDashboardView, "Edit Sex:", pet.getSex());
        if (newSex == null) return;
        String newStatus = JOptionPane.showInputDialog(adminDashboardView, "Edit Status:", pet.getStatus());
        if (newStatus == null) return;

        // Update model
        pet.setName(newName.trim());
        pet.setBreed(newBreed.trim());
        pet.setAge(newAge);
        pet.setSex(newSex.trim());
        pet.setStatus(newStatus.trim());

        // Update in database
        boolean success = adminDao.updatePet(pet);
        if (success) {
            JOptionPane.showMessageDialog(adminDashboardView, "Pet updated successfully!");
            // Reload table data
            loadPetTable();
        } else {
            JOptionPane.showMessageDialog(adminDashboardView, "Failed to update pet.");
        }
    }
}
    class ShareButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String url = "https://www.instagram.com";

        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (Exception ex) {
                // Show error dialog if opening the link fails
                JOptionPane.showMessageDialog(null, "Error opening link.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Show error dialog if Desktop browsing is not supported
            JOptionPane.showMessageDialog(null, "Opening links is not supported on your system.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        }    
    }
}    
    