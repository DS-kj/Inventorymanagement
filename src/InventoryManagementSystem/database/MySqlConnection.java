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

 @Override
public Connection openConnection() {
    String username = "root";
    String password = "databasesem2";
    String database = "Projectss";
    String url = "jdbc:mysql://localhost:3306/" + database + "?useSSL=false&serverTimezone=UTC";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("JDBC Driver loaded successfully.");
        Connection conn = DriverManager.getConnection(url, username, password);
        System.out.println("Connected to database!");
        return conn;
    } catch (ClassNotFoundException e) {
        System.err.println("JDBC Driver not found.");
        e.printStackTrace();
    } catch (SQLException e) {
        System.err.println("Connection failed.");
        e.printStackTrace();
    }
    return null;
}

    @Override
    public void closeConnection(Connection conn) {
 try{
            if(conn!=null && !conn.isClosed()){
            conn.close();
            }}catch(Exception e){
            }
           }

    @Override
    public ResultSet runQuery(Connection conn, String query) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int executeUpdate(Connection conn, String query) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Connection getconnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
