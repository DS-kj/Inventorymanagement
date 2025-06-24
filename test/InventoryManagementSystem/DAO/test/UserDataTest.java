package InventoryManagementSystem.DAO.test;

import InventoryManagementSystem.DAO.UserData;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserDataTest {

    @Test
    public void shouldCreateUserWithCorrectEmailAndPassword() {
        String email = "admin@example.com";
        String password = "admin123";

        UserData user = new UserData(email, password);

        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
    }
}
