/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package petadoptiontracker.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import petadoptiontracker.model.LoginRequest;
import petadoptiontracker.model.UserData;

public class UserDaoTest {

    String correctName = "Test Name";
    String correctEmail = "test12345@gmail.com";
    String password = "12345678";

    @Test
    public void registerWithNewCredentials() {
        UserData user = new UserData(correctName, correctEmail, password, new byte[0], "USER");
        UserDao dao = new UserDao();
        boolean result = dao.registerUser(user);
        Assert.assertTrue(result);
    }

    @Test
    public void registerWithExistingCredentials() {
        UserData user = new UserData(correctName, correctEmail, password, new byte[0], "USER");
        UserDao dao = new UserDao();
        boolean result = dao.registerUser(user);
        Assert.assertFalse(result);
    }

    @Test
    public void loginWithCorrectCreds() {
        LoginRequest req = new LoginRequest(correctEmail, password);
        UserDao dao = new UserDao();
        UserData user = dao.loginUser(req);
        Assert.assertNotNull(user);
        Assert.assertEquals(correctEmail, user.getEmail());
        Assert.assertEquals(correctName, user.getName());
    }
}
