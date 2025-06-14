package InventoryManagementSystem.DAO;

import InventoryManagementSystem.database.MySqlConnection;
import InventoryManagementSystem.model.AdminPanelModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDataDao {
    MySqlConnection mySql = new MySqlConnection();

    public UserDataDao() {
        createTableIfNotExists();
    }
    public List<AdminPanelModel> getAllUsers() {
    List<AdminPanelModel> users = new ArrayList<>();
    String query = "SELECT name, phonenumber FROM users";
    try (Connection conn = mySql.openConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        while (rs.next()) {
            String name = rs.getString("name");
            String phone = rs.getString("phonenumber");
            users.add(new AdminPanelModel(phone, name, "")); // Password is not needed for display
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return users;
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

            stmt.setString(1, user.getPhoneNumberEntry());
            stmt.setString(2, user.getUsernameAdminPanelEntry());
            stmt.setString(3, user.getPasswordAdminPanelEntry());

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

    
    public boolean deleteUser(String phoneNumber) {
    String sql = "DELETE FROM users WHERE phonenumber = ?";
    try (Connection conn = mySql.openConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, phoneNumber);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
   public boolean updateUser(String oldPhoneNumber, String newPhoneNumber, String newName, String newPassword) {
        String query = "UPDATE users SET phonenumber = ?, name = ?, password = ? WHERE phonenumber = ?";
        try (Connection conn = mySql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, newPhoneNumber);
            stmt.setString(2, newName);
            stmt.setString(3, newPassword);
            stmt.setString(4, oldPhoneNumber);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


