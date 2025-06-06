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

String username= "root";
       String password = "631116";
       String database = "Inv";
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection conn;
           conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ database, username,password);
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
 try{
            if(conn!=null && !conn.isClosed()){
            conn.close();
            }}catch(Exception e){
                e.printStackTrace();

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
    
}
