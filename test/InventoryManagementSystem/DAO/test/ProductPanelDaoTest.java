package InventoryManagementSystem.DAO.test;

import InventoryManagementSystem.dao.ProductPanelDao;
import InventoryManagementSystem.model.CategoryModel;
import InventoryManagementSystem.model.ProductModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ProductPanelDaoTest {

    @Test
    public void insertProduct_shouldAddNewProduct() {
        ProductPanelDao productPanelDao = new ProductPanelDao();
        // Make sure the category exists in DB, here id=1 for example
        CategoryModel testCategory = new CategoryModel(1, "TestCategory");

        ProductModel product = new ProductModel(0, "Test Product", testCategory, 10, 99.99);
        boolean result = productPanelDao.insertProduct(product);
        Assert.assertTrue("Product should be inserted successfully", result);
    }

    @Test
    public void getAllProducts_shouldReturnListOfProducts() {
        ProductPanelDao productPanelDao = new ProductPanelDao();
        List<ProductModel> products = productPanelDao.getAllProducts();

        Assert.assertNotNull("Product list should not be null", products);
        Assert.assertTrue("Product list should contain at least one product", products.size() > 0);
    }
}
