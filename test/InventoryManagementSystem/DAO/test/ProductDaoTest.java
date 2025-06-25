package InventoryManagementSystem.DAO.test;

import InventoryManagementSystem.DAO.ProductDao;
import InventoryManagementSystem.model.Product;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ProductDaoTest {

    ProductDao dao = new ProductDao();

    @Test
    public void testGetAllProducts() {
        ArrayList<Product> products = dao.getAllProducts();
        assertNotNull("Product list should not be null", products);
        // You can add more asserts based on your test DB, e.g. check size > 0 if you have data
        System.out.println("Number of products fetched: " + products.size());
    }

    @Test
    public void testSearchProducts() {
        String keyword = "someKeyword";  // change as per your test DB
        ArrayList<Product> results = dao.searchProducts(keyword);
        assertNotNull("Search result should not be null", results);
        // Optional: assert results size if you know expected count
        System.out.println("Number of products found with keyword '" + keyword + "': " + results.size());
    }
}
