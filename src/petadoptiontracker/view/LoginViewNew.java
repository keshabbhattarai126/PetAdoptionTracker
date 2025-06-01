/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package petadoptiontracker.view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

/**
 *
 * @author keshab
 */
public class LoginViewNew extends javax.swing.JFrame {

    /**
     * Creates new form EntryView
    
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jColorChooser1 = new javax.swing.JColorChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        forgotPasswordlabel = new javax.swing.JLabel();
        registerLabel = new javax.swing.JButton();
        emailTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        showPasswordButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

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

        passwordField.setText("      PASSWoRD");
        getContentPane().add(passwordField);
        passwordField.setBounds(340, 210, 170, 30);

        loginButton.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        loginButton.setText("LOGIN");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        getContentPane().add(loginButton);
        loginButton.setBounds(350, 250, 150, 30);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("________________________");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(280, 310, 120, 20);

        jLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("OR");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(420, 310, 37, 25);

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("_________________________");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(460, 310, 130, 20);

        forgotPasswordlabel.setForeground(new java.awt.Color(255, 255, 255));
        forgotPasswordlabel.setText("Forgot password?");
        getContentPane().add(forgotPasswordlabel);
        forgotPasswordlabel.setBounds(450, 280, 100, 20);

        registerLabel.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        registerLabel.setText("SIGN UP");
        getContentPane().add(registerLabel);
        registerLabel.setBounds(360, 350, 130, 30);

        emailTextField.setText("email");
        getContentPane().add(emailTextField);
        emailTextField.setBounds(340, 150, 170, 40);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagepicker/Screenshot 2025-05-23 183928.png"))); // NOI18N
        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(0, 0, 860, 530);

        showPasswordButton.setText("show");
        getContentPane().add(showPasswordButton);
        showPasswordButton.setBounds(530, 210, 72, 23);

        jButton2.setText("jButton2");
        getContentPane().add(jButton2);
        jButton2.setBounds(530, 210, 75, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel forgotPasswordlabel;
    private javax.swing.JButton jButton2;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton registerLabel;
    private javax.swing.JButton showPasswordButton;
    // End of variables declaration//GEN-END:variables

public javax.swing.JTextField getEmailTextField(){
        return emailTextField;
    }
     public javax.swing.JPasswordField getPasswordField(){
        return passwordField;
    }
    public void addRegisterListener(MouseListener listener){
        registerLabel.addMouseListener(listener);

    }
    public void addLoginUserListener(ActionListener listener){
        loginButton.addActionListener(listener);
    }
    public void togglePasswordField(boolean visible) {
        passwordField.setEchoChar(visible ? (char) 0 : '*');
        showPasswordButton.setText(visible ? "Hide" : "Show");
    }
    public void showPasswordButtonListener(ActionListener listener){
        showPasswordButton.addActionListener(listener);
    }
    public void forgotPassword(MouseListener listener){
        forgotPasswordlabel.addMouseListener(listener);
    }
}
