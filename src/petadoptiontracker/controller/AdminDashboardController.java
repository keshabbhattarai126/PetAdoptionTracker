package petadoptiontracker.controller;

import petadoptiontracker.model.UserData;
import petadoptiontracker.view.AdminDashboardView;

public class AdminDashboardController {
    private final AdminDashboardView adminDashboardView;

    public AdminDashboardController(AdminDashboardView view) {
        this.adminDashboardView = view;
        // Add admin-specific listeners here as you build features
    }

    public void open() {
        adminDashboardView.setVisible(true);
    }

    public void close() {
        adminDashboardView.dispose();
    }
}
