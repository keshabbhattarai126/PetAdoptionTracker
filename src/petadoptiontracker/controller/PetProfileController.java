/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petadoptiontracker.controller;

import petadoptiontracker.dao.AdminDao;
import petadoptiontracker.model.PetModel;
import petadoptiontracker.view.PetProfileView;

/**
 *
 * @author keshab
 */
public class PetProfileController {
    private final AdminDao adminDao;

    public PetProfileController() {
        this.adminDao = new AdminDao();
    }

    public void openPetProfile(int petId) {
        PetModel pet = adminDao.getPetById(petId);
        if (pet != null) {
            PetProfileView profileView = new PetProfileView(pet);
            profileView.setVisible(true);
        } else {
            // Optionally show an error dialog here
            System.out.println("Pet not found.");
        }
    }
}