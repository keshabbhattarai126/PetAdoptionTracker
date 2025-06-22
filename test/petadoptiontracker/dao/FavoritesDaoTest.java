/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package petadoptiontracker.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import petadoptiontracker.model.PetModel;

import java.util.List;

public class FavoritesDaoTest {

    private FavoritesDao favoritesDao;
    private int userId = 1;
    private int petId = 1;

    @Before
    public void setUp() {
        favoritesDao = new FavoritesDao();
    }

    @Test
    public void testAddToFavorites() {
        boolean result = favoritesDao.addToFavorites(userId, petId);
        Assert.assertTrue(result);
    }

    @Test
    public void testGetFavoritesByUser() {
        favoritesDao.addToFavorites(userId, petId);
        List<PetModel> favorites = favoritesDao.getFavoritesByUser(userId);
        boolean found = false;
        for (PetModel pet : favorites) {
            if (pet.getId() == petId) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found);
    }
}
