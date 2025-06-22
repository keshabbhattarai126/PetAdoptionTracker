package petadoptiontracker.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import petadoptiontracker.model.PetModel;

import java.util.List;

public class AdminDaoTest {

    private AdminDao adminDao;
    private PetModel testPet;

    @Before
    public void setUp() {
        adminDao = new AdminDao();
        testPet = new PetModel();
        testPet.setName("TestPet");
        testPet.setBreed("TestBreed");
        testPet.setAge(2);
        testPet.setSex("Male");
        testPet.setStatus("Available");
        testPet.setPhoto(new byte[0]);
        testPet.setPhoto2(new byte[0]);
        testPet.setPhoto3(new byte[0]);
    }

    @After
    public void tearDown() {
        List<PetModel> pets = adminDao.getAllPets();
        for (PetModel pet : pets) {
            if ("TestPet".equals(pet.getName())) {
                adminDao.deletePet(pet.getId());
            }
        }
    }

    @Test
    public void testAddPet() {
        boolean result = adminDao.addPet(testPet);
        Assert.assertTrue("Pet should be added successfully", result);
    }

    @Test
    public void testGetAllPets() {
        adminDao.addPet(testPet);
        List<PetModel> pets = adminDao.getAllPets();
        boolean found = false;
        for (PetModel pet : pets) {
            if ("TestPet".equals(pet.getName())) {
                found = true;
                break;
            }
        }
        Assert.assertTrue("Test pet should be found in all pets", found);
    }

    @Test
    public void testGetPetById() {
        adminDao.addPet(testPet);
        List<PetModel> pets = adminDao.getAllPets();
        PetModel foundPet = null;
        for (PetModel pet : pets) {
            if ("TestPet".equals(pet.getName())) {
                foundPet = adminDao.getPetById(pet.getId());
                break;
            }
        }
        Assert.assertNotNull("Should retrieve test pet by ID", foundPet);
        Assert.assertEquals("TestPet", foundPet.getName());
    }

    @Test
    public void testUpdatePet() {
        adminDao.addPet(testPet);
        List<PetModel> pets = adminDao.getAllPets();
        PetModel foundPet = null;
        for (PetModel pet : pets) {
            if ("TestPet".equals(pet.getName())) {
                foundPet = pet;
                break;
            }
        }
        Assert.assertNotNull("Test pet should exist", foundPet);
        foundPet.setBreed("UpdatedBreed");
        boolean updated = adminDao.updatePet(foundPet);
        Assert.assertTrue("Update should be successful", updated);

        PetModel updatedPet = adminDao.getPetById(foundPet.getId());
        Assert.assertEquals("UpdatedBreed", updatedPet.getBreed());
    }

    @Test
    public void testDeletePet() {
        adminDao.addPet(testPet);
        List<PetModel> pets = adminDao.getAllPets();
        int testPetId = -1;
        for (PetModel pet : pets) {
            if ("TestPet".equals(pet.getName())) {
                testPetId = pet.getId();
                break;
            }
        }
        Assert.assertTrue("Test pet should exist before delete", testPetId != -1);
        boolean deleted = adminDao.deletePet(testPetId);
        Assert.assertTrue("Delete should be successful", deleted);

        PetModel deletedPet = adminDao.getPetById(testPetId);
        Assert.assertNull("Deleted pet should not be found", deletedPet);
    }
}
