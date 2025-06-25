package InventoryManagementSystem.DAO.test;

import InventoryManagementSystem.dao.CustomerDao;
import InventoryManagementSystem.model.CustomerModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CustomerDaoTest {

    @Test
    public void getAllCustomers() {
        CustomerDao dao = new CustomerDao();
        List<CustomerModel> customers = dao.getAllCustomers();

        Assert.assertNotNull("Customer list should not be null", customers);

        // Optional: Log output for manual inspection
        for (CustomerModel customer : customers) {
            System.out.println("Customer ID: " + customer.getId());
            System.out.println("Name: " + customer.getName());
            System.out.println("Mobile: " + customer.getMobile());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("--------------------------");
        }
    }
}
