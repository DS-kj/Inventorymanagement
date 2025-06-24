package InventoryManagementSystem.dao.test;

import InventoryManagementSystem.dao.CustomerPanelDao;
import InventoryManagementSystem.model.CustomerPanelModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CustomerPanelDaoTest {

    private final CustomerPanelDao dao = new CustomerPanelDao();

    @Test
    public void testAddCustomer() {
        CustomerPanelModel customer = new CustomerPanelModel(
                0, "JUnit Test", "9999999999", "junit@example.com"
        );

        boolean result = dao.addCustomer(customer);
        Assert.assertTrue("Customer should be added successfully", result);
    }

    @Test
    public void testGetAllCustomers() {
        List<CustomerPanelModel> customers = dao.getAllCustomers();
        Assert.assertNotNull("Customer list should not be null", customers);
        for (CustomerPanelModel customer : customers) {
            Assert.assertNotNull("Customer name should not be null", customer.getName());
            Assert.assertNotNull("Customer mobile should not be null", customer.getMobileNumber());
            Assert.assertNotNull("Customer email should not be null", customer.getEmail());
        }
    }

    @Test
    public void testDeleteCustomer() {
        // First, add a test customer to delete
        CustomerPanelModel customer = new CustomerPanelModel(
                0, "ToDelete", "1234567890", "delete@example.com"
        );
        boolean added = dao.addCustomer(customer);
        Assert.assertTrue("Customer should be added for deletion test", added);

        // Get the newly added customer's ID
        int deleteId = -1;
        List<CustomerPanelModel> customers = dao.getAllCustomers();
        for (CustomerPanelModel c : customers) {
            if (c.getName().equals("ToDelete") && c.getEmail().equals("delete@example.com")) {
                deleteId = c.getId();
                break;
            }
        }

        Assert.assertTrue("Newly added test customer should be found", deleteId > 0);

        // Now delete the customer
        boolean deleted = dao.deleteCustomer(deleteId);
        Assert.assertTrue("Customer should be deleted successfully", deleted);
    }
}
