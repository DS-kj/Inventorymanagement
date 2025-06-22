package InventoryManagementSystem.dao;

import InventoryManagementSystem.database.MySqlConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetupDao {
    private final MySqlConnection mySql = new MySqlConnection();

    public void createAllTablesIfNotExist() {
        try (Connection conn = mySql.openConnection();
             Statement stmt = conn.createStatement()) {

            // Category table
            String categoryTable = """
                CREATE TABLE IF NOT EXISTS category (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(255) NOT NULL UNIQUE
                )
            """;

            // Products table
            String productsTable = """
                CREATE TABLE IF NOT EXISTS products (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(100) NOT NULL,
                    category_id INT NOT NULL,
                    quantity INT NOT NULL,
                    price DOUBLE NOT NULL,
                    FOREIGN KEY (category_id) REFERENCES category(id)
                )
            """;

            // Customers table
            String customersTable = """
                CREATE TABLE IF NOT EXISTS customers (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    mobile VARCHAR(20),
                    email VARCHAR(100)
                )
            """;

            // Users table
            String usersTable = """
                CREATE TABLE IF NOT EXISTS users (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    phonenumber VARCHAR(15),
                    name VARCHAR(100),
                    password VARCHAR(100)
                )
            """;

            // Orders table
            String ordersTable = """
                CREATE TABLE IF NOT EXISTS orders (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    customer_id INT,
                    order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (customer_id) REFERENCES customers(id)
                )
            """;

            // Order items table
            String orderItemsTable = """
                CREATE TABLE IF NOT EXISTS order_items (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    order_id INT,
                    product_id INT,
                    quantity INT,
                    price DOUBLE,
                    FOREIGN KEY (order_id) REFERENCES orders(id),
                    FOREIGN KEY (product_id) REFERENCES products(id)
                )
            """;

            // Execute all table creation queries
            stmt.execute(categoryTable);
            stmt.execute(productsTable);
            stmt.execute(customersTable);
            stmt.execute(usersTable);
            stmt.execute(ordersTable);
            stmt.execute(orderItemsTable);

            System.out.println("All tables created or already exist.");

        } catch (SQLException e) {
            System.err.println("Error creating tables: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
