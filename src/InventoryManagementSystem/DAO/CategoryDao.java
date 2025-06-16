package InventoryManagementSystem.DAO;

import InventoryManagementSystem.model.CategoryModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    Connection conn;

    public CategoryDao() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addCategory(CategoryModel category) {
        String sql = "INSERT INTO category (name) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category.getName());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateCategory(CategoryModel category) {
        String sql = "UPDATE category SET name = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category.getName());
            stmt.setInt(2, category.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<CategoryModel> getAllCategories() {
        List<CategoryModel> list = new ArrayList<>();
        String sql = "SELECT * FROM category";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new CategoryModel(rs.getInt("id"), rs.getString("name")));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
}
