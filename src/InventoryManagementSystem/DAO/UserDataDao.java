package InventoryManagementSystem.DAO;

import InventoryManagementSystem.database.MySqlConnection;
import InventoryManagementSystem.model.AdminPanelModel;

import java.sql.*;

public class UserDataDao {
    MySqlConnection mySql = new MySqlConnection();

    public UserDataDao() {
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String createTableQuery = """
            CREATE TABLE IF NOT EXISTS users (
                id INT AUTO_INCREMENT PRIMARY KEY,
                phonenumber VARCHAR(15),
                name VARCHAR(100),
                password VARCHAR(100)
            )
        """;

        try (Connection conn = mySql.openConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean register(AdminPanelModel user) {
        String query = "INSERT INTO users (phonenumber, name, password) VALUES (?, ?, ?)";
        try (Connection conn = mySql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getPhoneNumber());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getPassword());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet fetchAllUsers() {
        String query = "SELECT name, phonenumber FROM users";
        try {
            Connection conn = mySql.openConnection();
            return conn.createStatement().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
    