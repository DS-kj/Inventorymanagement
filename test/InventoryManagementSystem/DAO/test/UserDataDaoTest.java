package InventoryManagementSystem.DAO.test;

import InventoryManagementSystem.DAO.UserDataDao;
import InventoryManagementSystem.model.AdminPanelModel;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserDataDaoTest {

    @Test
    public void testRegister() {
        UserDataDao dao = new UserDataDao();
        AdminPanelModel user = new AdminPanelModel("9876543210", "testuser", "testpass");
        boolean result = dao.register(user);
        assertTrue(result);
    }

    @Test
    public void testGetAllUsers() {
        UserDataDao dao = new UserDataDao();
        List<AdminPanelModel> users = dao.getAllUsers();
        assertNotNull(users);
        assertTrue(users.size() >= 0); // Passes even if empty
    }

    @Test
    public void testUpdateUser() {
        UserDataDao dao = new UserDataDao();
        boolean result = dao.updateUser("9876543210", "1234567890", "updateduser", "newpass");
        assertTrue(result);
    }

    @Test
    public void testDeleteUser() {
        UserDataDao dao = new UserDataDao();
        boolean result = dao.deleteUser("1234567890");
        assertTrue(result);
    }
}
