/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InventoryManagementSystem.database;

import java.sql.*;

/**
 *
 * @author ACER
 */
public interface DbConnection {
     Connection openConnection();
    void closeConnection(Connection conn);
    
}
