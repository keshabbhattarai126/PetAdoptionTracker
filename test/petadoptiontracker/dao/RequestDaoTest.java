/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package petadoptiontracker.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class RequestDaoTest {

    private RequestDao requestDao;
    private int userId = 1;
    private int petId = 1;

    @Before
    public void setUp() {
        requestDao = new RequestDao();
    }

    @Test
    public void testCreateRequest() {
        boolean result = requestDao.createRequest(userId, petId);
        Assert.assertTrue(result);
    }

    @Test
    public void testGetAllRequestsWithUserAndPet() {
        requestDao.createRequest(userId, petId);
        List<Map<String, Object>> requests = requestDao.getAllRequestsWithUserAndPet();
        Assert.assertNotNull(requests);
        Assert.assertTrue(requests.size() > 0);
    }
}
