package InventoryManagementSystem.DAO.test;

import InventoryManagementSystem.DAO.ViewOrdersDao;
import InventoryManagementSystem.model.ViewOrdersModel;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ViewOrdersDaoTest {

    @Test
    public void testGetAllCustomers_returnsList() {
        ViewOrdersDao dao = new ViewOrdersDao();
        List<ViewOrdersModel> customers = dao.getAllCustomers();

        assertNotNull(customers);
        for (ViewOrdersModel customer : customers) {
            assertNotNull(customer.getName());
            assertNotNull(customer.getMobileNumber());
            assertNotNull(customer.getEmail());
            assertTrue(customer.getId() > 0);
        }
    }
}
