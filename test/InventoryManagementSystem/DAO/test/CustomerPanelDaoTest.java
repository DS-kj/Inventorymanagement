package InventoryManagementSystem.DAO.test;

import InventoryManagementSystem.dao.CustomerPanelDao;
import InventoryManagementSystem.model.CustomerPanelModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CustomerPanelDaoTest {

    String testName = "Test Customer";
    String testMobile = "9812345678";
    String testEmail = "test@example.com";

    @Test
    public void addNewCustomer() {
        CustomerPanelDao dao = new CustomerPanelDao();
        CustomerPanelModel customer = new CustomerPanelModel(0, testName, testMobile, testEmail);

        boolean result = dao.addCustomer(customer);
        Assert.assertTrue("Customer should be added successfully", result);
    }

    @Test
    public void getAllCustomers() {
        CustomerPanelDao dao = new CustomerPanelDao();
        List<CustomerPanelModel> customers = dao.getAllCustomers();

        Assert.assertNotNull("Customer list should not be null", customers);
        Assert.assertTrue("Customer list size should be >= 0", customers.size() >= 0);
    }

    @Test
    public void deleteLastCustomer() {
        CustomerPanelDao dao = new CustomerPanelDao();
        List<CustomerPanelModel> customers = dao.getAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("No customer to delete; skipping test.");
            return;
        }

        int lastId = customers.get(customers.size() - 1).getId();
        boolean result = dao.deleteCustomer(lastId);

        Assert.assertTrue("Customer should be deleted successfully", result);
    }
}
