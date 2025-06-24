package InventoryManagementSystem.DAO.test;

import InventoryManagementSystem.DAO.ProductAndCartDao;
import InventoryManagementSystem.model.ProductAndCartModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductAndCartDaoTest {

    @Test
    public void getAllProducts() {
        ProductAndCartDao dao = new ProductAndCartDao();
        List<ProductAndCartModel> products = dao.getAllProducts();

        Assert.assertNotNull("Products list should not be null", products);
        Assert.assertTrue("There should be at least 0 products", products.size() >= 0);
    }

    @Test
    public void saveOrder() {
        ProductAndCartDao dao = new ProductAndCartDao();

        // Prepare dummy cart items - ensure product IDs exist in DB
        List<ProductAndCartModel> cartItems = new ArrayList<>();
        cartItems.add(new ProductAndCartModel(1, "Sample Product", "Sample Category", 2, 100.0));

        int customerId = 1;  // Set to an existing customer id in your DB

        boolean result = dao.saveOrder(cartItems, customerId);
        Assert.assertTrue("Order should be saved successfully", result);
    }

    @Test
    public void getLatestOrderId() {
        ProductAndCartDao dao = new ProductAndCartDao();
        int latestId = dao.getLatestOrderId();

        Assert.assertTrue("Latest order ID should be zero or positive", latestId >= 0);
    }
}
