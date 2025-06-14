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
        jButton4 = new javax.swing.JButton();
        signOutButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nameResult = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        emailResult = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        petTable = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        requestButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(950, 535));
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
        viewPetTab.setBounds(34, 187, 160, 31);

        dashboardButton.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        dashboardButton.setText("Dashboard");
        getContentPane().add(dashboardButton);
        dashboardButton.setBounds(34, 126, 160, 31);

        jButton4.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jButton4.setText("Profile Setting");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(34, 243, 160, 31);

        signOutButton.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        signOutButton.setText("Sign Out");
        getContentPane().add(signOutButton);
        signOutButton.setBounds(34, 303, 160, 31);

        jLabel2.setText("This is Dashboard");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(610, 60, 94, 16);
        getContentPane().add(searchField);
        searchField.setBounds(320, 60, 195, 22);

        searchButton.setText("Search");
        getContentPane().add(searchButton);
        searchButton.setBounds(520, 60, 72, 23);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(371, 303, 0, 0);

        jLabel4.setText("Name");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(290, 450, 32, 16);
        getContentPane().add(nameResult);
        nameResult.setBounds(340, 450, 130, 22);

        jLabel5.setText("Email");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(490, 450, 30, 16);
        getContentPane().add(emailResult);
        emailResult.setBounds(540, 450, 189, 22);

        jPanel1.setBackground(new java.awt.Color(38, 78, 201));
        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 270, 510);

        jPanel3.setLayout(null);

        jLabel9.setText("This is tab1");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(120, 70, 59, 16);

        jTabbedPane4.addTab("tab1", jPanel3);

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

        jTabbedPane4.addTab("tab2", jPanel4);

        jPanel5.setLayout(null);

        jLabel11.setText("This is tab 3");
        jPanel5.add(jLabel11);
        jLabel11.setBounds(210, 40, 110, 40);

        jTabbedPane4.addTab("tab3", jPanel5);

        getContentPane().add(jTabbedPane4);
        jTabbedPane4.setBounds(290, 90, 450, 290);

        requestButton.setText("Request");
        getContentPane().add(requestButton);
        requestButton.setBounds(740, 330, 72, 23);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton4ActionPerformed

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
    private javax.swing.JTextField emailResult;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTextField nameResult;
    private javax.swing.JTable petTable;
    private javax.swing.JLabel photoLabel;
    private javax.swing.JButton requestButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JButton signOutButton;
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

    public void setSearchResult(String name, String email) {
        nameResult.setText(name);
        emailResult.setText(email);
    }

    public void clearSearchResult() {
        nameResult.setText("");
        emailResult.setText("");
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
}
