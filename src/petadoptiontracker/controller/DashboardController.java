
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author keshab
 */
package petadoptiontracker.controller;
import petadoptiontracker.view.DashboardView;

import petadoptiontracker.view.DashboardView;

/**
 *
 * @author keshab
 */
public class DashboardController {
    DashboardView dashboardView;
    public DashboardController(DashboardView dashboardView){
        this.dashboardView=dashboardView;
    }
    public void open(){
        this.dashboardView.setVisible(true);
    }
    public void close(){
        this.dashboardView.dispose();
    }
    
}
