package InventoryManagementSystem.database;

import java.sql.*;

public class MySqlConnection implements DbConnection {

    @Override
    public Connection openConnection() {
        String username = "root";
        String password = "1618014350569";
        String database = "inventory_db";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, username, password);
            
            if (conn != null && !conn.isClosed()) {
                System.out.println("Database connected successfully.");
            }

            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Failed to connect to the database!");
            return null;
        }
    }

    @Override
    public void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
        }
    }

    @Override
    public ResultSet runQuery(Connection conn, String query) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int executeUpdate(Connection conn, String query) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
