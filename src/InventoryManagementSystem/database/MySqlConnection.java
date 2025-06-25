/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InventoryManagementSystem.database;

import java.sql.*;
/**
 *
 * @author ACER
 */
public class MySqlConnection implements DbConnection {
private final String username = "root";
    private final String password = "1618014350569";
    private final String database = "inventorymanagementsystem";
    @Override
    public Connection openConnection() {

//String username= "root";
//       String password = "631116";
//       String database = "inventorymanagementsystem";
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection conn;
           conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ database, username,password);
       return conn;}
       catch (Exception e){
           return null;    }
    }

    @Override
    public void closeConnection(Connection conn) {
 try{
            if(conn!=null && !conn.isClosed()){
            conn.close();
            }}catch(Exception e){
            }
           }
public void createDatabaseIfNotExists() {
        String url = "jdbc:mysql://localhost:3306/"; // no database specified
        String sql = "CREATE DATABASE IF NOT EXISTS " + database;

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Database `" + database + "` created or already exists.");

        } catch (SQLException e) {
            System.err.println("Error creating database: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public ResultSet runQuery(Connection conn, String query) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public int executeUpdate(Connection conn, String query) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Connection getconnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
