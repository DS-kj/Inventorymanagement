package InventoryManagementSystem.DAO;

import InventoryManagementSystem.database.MySqlConnection;
import InventoryManagementSystem.model.LoginRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    MySqlConnection mySql = new MySqlConnection();

    public UserData login(LoginRequest loginRequest) {
        String sql = "SELECT name, password FROM users WHERE username = ? AND password = ?";

        try (Connection conn = mySql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, loginRequest.getUsername());
            stmt.setString(2, loginRequest.getPassword());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String email = rs.getString("name");
                    String password = rs.getString("password");

                    return new UserData(email, password);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
