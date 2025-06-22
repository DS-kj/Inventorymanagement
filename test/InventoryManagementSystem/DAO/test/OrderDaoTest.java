package InventoryManagementSystem.DAO.test;

import InventoryManagementSystem.DAO.OrderDao;
import InventoryManagementSystem.model.OrderModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class OrderDaoTest {

    private final OrderDao dao = new OrderDao();

    @Test
    public void testGetOrdersByCustomerId() {
        int testCustomerId = 1; // Replace with a valid customer ID from your DB

        List<OrderModel> orders = dao.getOrdersByCustomerId(testCustomerId);

        Assert.assertNotNull("Orders list should not be null", orders);
        Assert.assertTrue("Orders list size should be zero or more", orders.size() >= 0);

        for (OrderModel order : orders) {
            Assert.assertTrue("Order ID should be positive", order.getId() > 0);
            Assert.assertNotNull("Product name should not be null", order.getProductName());
            Assert.assertTrue("Quantity should be positive", order.getQuantity() > 0);
            Assert.assertTrue("Price should be zero or positive", order.getPrice() >= 0);
            Assert.assertNotNull("Order date should not be null", order.getOrderDate());
        }
    }
}
