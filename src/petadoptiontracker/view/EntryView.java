/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package petadoptiontracker.view;

/**
 *
 * @author keshab
 */
public class EntryView extends javax.swing.JFrame {

    /**
     * Creates new form EntryView
    
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jColorChooser1 = new javax.swing.JColorChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagepicker/istockphoto-1404131751-612x612.jpg"))); // NOI18N
        jLabel6.setText("jLabel6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 51, 255));
        setSize(new java.awt.Dimension(800, 535));
        getContentPane().setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagepicker/HomePhoto.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(-60, -60, 40, 600);

        jButton1.setText("USERNAME");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(340, 170, 170, 30);

        jPasswordField1.setText("      PASSWoRD");
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(340, 210, 170, 30);

        jButton2.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jButton2.setText("LOGIN");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(350, 250, 150, 30);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("________________________");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(280, 320, 120, 20);

        jLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("OR");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(410, 320, 37, 25);

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("_________________________");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(440, 320, 130, 20);

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Forgot password?");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(450, 280, 100, 20);

        jButton3.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jButton3.setText("SIGN UP");
        getContentPane().add(jButton3);
        jButton3.setBounds(360, 350, 130, 30);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagepicker/Screenshot 2025-05-23 183928.png"))); // NOI18N
        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(-3, 0, 860, 530);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPasswordField jPasswordField1;
    // End of variables declaration//GEN-END:variables
}
