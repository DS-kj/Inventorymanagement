package InventoryManagementSystem.database;

import java.sql.*;

public class MySqlConnection implements DbConnection {

    @Override
    public Connection openConnection() {
        String username = "localhost";
        String password = "1";
        String database = "inventory_db";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            // Fix: added missing slash before database name
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, username, password);
            
            if (conn != null && !conn.isClosed()) {
                System.out.println("Database connected successfully.");
            }

            return conn;
        } catch (Exception e) {
            System.out.println("Failed to connect to the database!");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
