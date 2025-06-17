package InventoryManagementSystem.DAO;
 
import InventoryManagementSystem.database.MySqlConnection;
import InventoryManagementSystem.model.CategoryModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
 
public class CategoryDao {
    MySqlConnection mySql = new MySqlConnection();
 
    public CategoryDao() {
        createTableIfNotExists();
    }
 
    private void createTableIfNotExists() {
        String createTableQuery = """
            CREATE TABLE IF NOT EXISTS category (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(255) NOT NULL UNIQUE
            )
        """;
        try (Connection conn = mySql.openConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public boolean addCategory(CategoryModel category) {
        String query = "INSERT INTO category (name) VALUES (?)";
        try (Connection conn = mySql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, category.getName());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
 
    public List<CategoryModel> getAllCategories() {
        List<CategoryModel> categories = new ArrayList<>();
        String query = "SELECT * FROM category";
        try (Connection conn = mySql.openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                categories.add(new CategoryModel(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
    public boolean deleteCategory(int ID) {
   String sql = "DELETE FROM category WHERE ID = ?";
   try (Connection conn = mySql.openConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
       stmt.setInt(1, ID);
       return stmt.executeUpdate() > 0;
   } catch (SQLException e) {
       e.printStackTrace();
       return false;
   }
}
}