package InventoryManagementSystem.DAO.test;

import InventoryManagementSystem.DAO.ProductAndCartDao;
import InventoryManagementSystem.model.ProductAndCartModel;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class ProductAndCartDaoTest {

    private final ProductAndCartDao dao = new ProductAndCartDao();

    @Test
    public void testGetAllProducts() {
        List<ProductAndCartModel> products = dao.getAllProducts();

        Assert.assertNotNull("Product list should not be null", products);
        Assert.assertTrue("Product list size should be zero or more", products.size() >= 0);

        for (ProductAndCartModel product : products) {
            Assert.assertTrue("Product id should be positive", product.getId() > 0);
            Assert.assertNotNull("Product name should not be null", product.getName());
            Assert.assertNotNull("Category name should not be null", product.getCategory());
            Assert.assertTrue("Quantity should be zero or more", product.getQuantity() >= 0);
            Assert.assertTrue("Price should be zero or more", product.getPrice() >= 0);
        }
    }

    @Test
    public void testGetLatestOrderId() {
        int latestOrderId = dao.getLatestOrderId();
        Assert.assertTrue("Latest order ID should be zero or positive", latestOrderId >= 0);
    }

    @Test
    public void testSaveOrder() {
        // Prepare test data
        List<ProductAndCartModel> cartItems = new ArrayList<>();
        cartItems.add(new ProductAndCartModel(1, "Test Product", "Test Category", 1, 10.0));

        int testCustomerId = 1;  // Replace with a valid customer id in your DB

        boolean result = dao.saveOrder(cartItems, testCustomerId);
        Assert.assertTrue("Order should be saved successfully", result);
    }
}
