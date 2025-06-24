package InventoryManagementSystem.DAO.test;

import InventoryManagementSystem.DAO.UserDao;
import InventoryManagementSystem.model.LoginRequest;
import org.junit.Assert;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void login_shouldReturnUserData_whenCredentialsAreCorrect() {
        UserDao dao = new UserDao();
        LoginRequest request = new LoginRequest("existingUsername", "correctPassword");

        var userData = dao.login(request);

        Assert.assertNotNull("Login should return user data for valid credentials", userData);
        Assert.assertEquals("Username should match", "existingUsername", userData.getEmail());
        Assert.assertEquals("Password should match", "correctPassword", userData.getPassword());
    }

    @Test
    public void login_shouldReturnNull_whenCredentialsAreIncorrect() {
        UserDao dao = new UserDao();
        LoginRequest request = new LoginRequest("wrongUsername", "wrongPassword");

        var userData = dao.login(request);

        Assert.assertNull("Login should return null for invalid credentials", userData);
    }
}
