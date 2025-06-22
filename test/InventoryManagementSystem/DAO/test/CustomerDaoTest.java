package InventoryManagementSystem.dao.test;

import InventoryManagementSystem.dao.CustomerDao;
import InventoryManagementSystem.model.CustomerModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CustomerDaoTest {

    private final CustomerDao dao = new CustomerDao();

    @Test
    public void testGetAllCustomers() {
        List<CustomerModel> customers = dao.getAllCustomers();

        // Ensure list is not null
        Assert.assertNotNull("Customer list should not be null", customers);

        // Optionally print the count
        System.out.println("Total customers retrieved: " + customers.size());

        // Validate fields for each customer
        for (CustomerModel customer : customers) {
            Assert.assertTrue("Customer ID should be positive", customer.getId() > 0);
            Assert.assertNotNull("Customer name should not be null", customer.getName());
            Assert.assertNotNull("Customer mobile should not be null", customer.getMobile());
            Assert.assertNotNull("Customer email should not be null", customer.getEmail());
        }
    }
}
