/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package petadoptiontracker.view;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import petadoptiontracker.controller.DashboardController;
import petadoptiontracker.dao.UserDao;
import petadoptiontracker.model.PetModel;
import petadoptiontracker.model.UserData;

/**
 *
 * @author keshab
 */
public class DashboardView extends javax.swing.JFrame {
    

    public static void setPetTableData(List<PetModel> petList) {
        
      
        throw new UnsupportedOperationException("Not supported yet."); 
        
    }
    
    public DashboardView(UserData user) {
        initComponents();
        jLabel1.setText("Welcome, " + user.getName() +" "+ user.getRole() + "!");
        jLabel11.setText(user.getName());
        jLabel12.setText(user.getEmail());
        byte[] imageData = user.getImage();
        if (imageData != null) {
            ImageIcon imageIcon = new ImageIcon(imageData);
            Image img = imageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(img);
            photoLabel.setIcon(imageIcon);
            
        }
    }

    public DashboardView() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        photoLabel = new javax.swing.JLabel();
        viewPetTab = new javax.swing.JButton();
        dashboardButton = new javax.swing.JButton();
        profileButton = new javax.swing.JButton();
        signOutButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        petTable = new javax.swing.JTable();
        favoriteButton = new javax.swing.JButton();
        requestButton = new javax.swing.JButton();
        viewPetProfileButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        requestButton1 = new javax.swing.JButton();
        favoriteButton1 = new javax.swing.JButton();
        heartButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(950, 535));
        getContentPane().setLayout(null);

        jLabel1.setText("Dashboard");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(360, 10, 260, 20);

        photoLabel.setText("jLabel2");
        photoLabel.setMaximumSize(new java.awt.Dimension(150, 150));
        photoLabel.setMinimumSize(new java.awt.Dimension(150, 150));
        getContentPane().add(photoLabel);
        photoLabel.setBounds(68, 34, 80, 80);

        viewPetTab.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        viewPetTab.setText("View Pet");
        getContentPane().add(viewPetTab);
        viewPetTab.setBounds(34, 187, 160, 29);

        dashboardButton.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        dashboardButton.setText("Dashboard");
        getContentPane().add(dashboardButton);
        dashboardButton.setBounds(34, 126, 160, 29);

        profileButton.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        profileButton.setText("Profile Setting");
        profileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileButtonActionPerformed(evt);
            }
        });
        getContentPane().add(profileButton);
        profileButton.setBounds(34, 243, 160, 29);

        signOutButton.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        signOutButton.setText("Sign Out");
        getContentPane().add(signOutButton);
        signOutButton.setBounds(34, 303, 160, 29);

        jLabel2.setText("This is Dashboard");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(610, 60, 106, 17);
        getContentPane().add(searchField);
        searchField.setBounds(320, 60, 195, 23);

        searchButton.setText("Search");
        getContentPane().add(searchButton);
        searchButton.setBounds(520, 60, 72, 23);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(371, 303, 0, 0);

        jPanel1.setBackground(new java.awt.Color(38, 78, 201));
        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 270, 510);

        jPanel3.setLayout(null);

        jLabel9.setText("This is tab1");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(120, 70, 68, 17);

        jTabbedPane4.addTab("Dashboard", jPanel3);

        jPanel4.setLayout(null);

        petTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(petTable);

        jPanel4.add(jScrollPane1);
        jScrollPane1.setBounds(-30, 0, 460, 280);

        favoriteButton.setText("Favorite");
        jPanel4.add(favoriteButton);
        favoriteButton.setBounds(470, 150, 76, 23);

        requestButton.setText("Request");
        requestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestButtonActionPerformed(evt);
            }
        });
        jPanel4.add(requestButton);
        requestButton.setBounds(470, 200, 79, 23);

        viewPetProfileButton.setText("View Profile");
        jPanel4.add(viewPetProfileButton);
        viewPetProfileButton.setBounds(470, 100, 100, 20);

        jTabbedPane4.addTab("View Pet", jPanel4);

        jPanel5.setLayout(null);

        jLabel11.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel11.setText("Name text");
        jPanel5.add(jLabel11);
        jLabel11.setBounds(70, 20, 110, 30);

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel6.setText("Preference:");
        jPanel5.add(jLabel6);
        jLabel6.setBounds(10, 160, 120, 30);

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel12.setText("Email text");
        jPanel5.add(jLabel12);
        jLabel12.setBounds(340, 20, 200, 30);

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel7.setText("Email:");
        jPanel5.add(jLabel7);
        jLabel7.setBounds(280, 20, 80, 30);

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel8.setText("Name:");
        jPanel5.add(jLabel8);
        jLabel8.setBounds(10, 20, 80, 30);

        jComboBox1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Other" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBox1);
        jComboBox1.setBounds(80, 90, 103, 32);

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel10.setText("Gender:");
        jPanel5.add(jLabel10);
        jLabel10.setBounds(10, 90, 70, 30);
        jPanel5.add(jTextField1);
        jTextField1.setBounds(340, 90, 120, 30);

        jLabel13.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel13.setText("Phone:");
        jPanel5.add(jLabel13);
        jLabel13.setBounds(280, 90, 70, 30);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        jPanel5.add(jScrollPane3);
        jScrollPane3.setBounds(120, 160, 300, 40);

        jButton1.setText("Submit");
        jPanel5.add(jButton1);
        jButton1.setBounds(200, 220, 110, 30);

        jTabbedPane4.addTab("Profile Setting", jPanel5);

        getContentPane().add(jTabbedPane4);
        jTabbedPane4.setBounds(290, 90, 610, 300);

        requestButton1.setBackground(new java.awt.Color(0, 255, 204));
        requestButton1.setText("Request");
        requestButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestButtonActionPerformed(evt);
            }
        });
        getContentPane().add(requestButton1);
        requestButton1.setBounds(740, 330, 79, 23);

        favoriteButton1.setBackground(new java.awt.Color(255, 51, 255));
        favoriteButton1.setText("Favorite");
        getContentPane().add(favoriteButton1);
        favoriteButton1.setBounds(740, 300, 76, 23);

        heartButton.setBackground(new java.awt.Color(255, 102, 255));
        heartButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagepicker/heart pic.png"))); // NOI18N
        heartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                heartButtonActionPerformed(evt);
            }
        });
        getContentPane().add(heartButton);
        heartButton.setBounds(734, 10, 46, 40);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void profileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileButtonActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_profileButtonActionPerformed

    private void requestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_requestButtonActionPerformed

    private void heartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_heartButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_heartButtonActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dashboardButton;
    private javax.swing.JButton favoriteButton;
    private javax.swing.JButton favoriteButton1;
    private javax.swing.JButton heartButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable petTable;
    private javax.swing.JLabel photoLabel;
    private javax.swing.JButton profileButton;
    private javax.swing.JButton requestButton;
    private javax.swing.JButton requestButton1;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JButton signOutButton;
    private javax.swing.JButton viewPetProfileButton;
    private javax.swing.JButton viewPetTab;
    // End of variables declaration//GEN-END:variables

    public void addMyRequestButtonListener(ActionListener listener) {
        viewPetTab.addActionListener(listener);
    }

    public void addSearchButtonListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    public String getSearchInput() {
        return searchField.getText();
    }

    public void addSignOutButtonListener(ActionListener listener) {
        signOutButton.addActionListener(listener);
    }

    public JTabbedPane getTabbedPane() {
    return jTabbedPane4;
}


    public void viewPetTabButtonListener(ActionListener listener) {
        viewPetTab.addActionListener(listener);
        
    }
    public void setTableData(List<PetModel> petList) {
    String[] columns = {"ID", "Name", "Breed", "Age", "Sex", "Status"};
    DefaultTableModel model = new DefaultTableModel(columns, 0);
 
    for (PetModel pet : petList) {
        Object[] row = {
            pet.getId(),
            pet.getName(),
            pet.getBreed(),
            pet.getAge(),
            pet.getSex(),
            pet.getStatus()
        };
        model.addRow(row);
    }
    petTable.setModel(model);
}
    public void requestButtonListener(ActionListener listener){
        requestButton.addActionListener(listener);
    }
    public JTable getPetTable() {
    return petTable;
}
    public void addFavoriteButtonListener(ActionListener listener) {
    favoriteButton.addActionListener(listener);
}
public void addHeartButtonListener(ActionListener listener) {
    heartButton.addActionListener(listener);
}
public void addDashboardTabButtonListener(ActionListener listener) {
    dashboardButton.addActionListener(listener);
}



public void addProfileTabButtonListener(ActionListener listener) {
    profileButton.addActionListener(listener);
}


   public void addViewPetProfileListener(ActionListener listener) {
    viewPetProfileButton.addActionListener(listener);
}
}
