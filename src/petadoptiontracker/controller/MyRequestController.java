/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petadoptiontracker.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import petadoptiontracker.view.DashboardView;
import petadoptiontracker.view.MyRequestView;



/**
 *
 * @author keshab
 */
public class MyRequestController {
    private MyRequestView myRequestView;

    public MyRequestController(MyRequestView myRequestView) {
        this.myRequestView = myRequestView;

        // Register the dashboard button listener
        this.myRequestView.addDashboardButtonListener(new DashboardButtonListener());
    }

    public void open() {
        this.myRequestView.setVisible(true);
    }

    public void close() {
        this.myRequestView.dispose();
    }

    // Inner class to handle Dashboard button click
    class DashboardButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DashboardView dashboardView = new DashboardView();
            DashboardController dashboardController = new DashboardController(dashboardView);
            dashboardController.open();
            close(); // Close MyRequest view
        }
    }
}