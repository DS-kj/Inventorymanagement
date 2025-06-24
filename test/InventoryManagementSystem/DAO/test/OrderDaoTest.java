package InventoryManagementSystem.DAO.test;

import InventoryManagementSystem.DAO.OrderDao;
import InventoryManagementSystem.model.OrderModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class OrderDaoTest {

    // Use a customerId known to exist in your test DB or insert test data before running this
    private final int testCustomerId = 1;

    @Test
    public void testGetOrdersByCustomerId() {
        OrderDao orderDao = new OrderDao();
        List<OrderModel> orders = orderDao.getOrdersByCustomerId(testCustomerId);

        Assert.assertNotNull("Orders list should not be null", orders);
        // Depending on your data, it might be empty or not
        // Assert that the list size is >= 0 just to confirm query runs
        Assert.assertTrue("Orders list size should be >= 0", orders.size() >= 0);

        // Optional: if you know you have orders, check the contents of the first order
        if (!orders.isEmpty()) {
            OrderModel firstOrder = orders.get(0);
            Assert.assertTrue("Order ID should be positive", firstOrder.getOrderId() > 0);
            Assert.assertNotNull("Product name should not be null", firstOrder.getProductName());
            Assert.assertTrue("Quantity should be positive", firstOrder.getQuantity() > 0);
            Assert.assertTrue("Price should be positive", firstOrder.getPrice() > 0);
            Assert.assertNotNull("Order date should not be null", firstOrder.getOrderDate());
        }
    }
}
