/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package InventoryManagementSystem;

import InventoryManagementSystem.DAO.CategoryDao;
import InventoryManagementSystem.controller.CategoryController;
//import InventoryManagementSystem.view.AdminPanel;
import InventoryManagementSystem.view.Category;


/**
 *
 * @author ACER
 */
public class InventoryManagementMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
           Category view = new Category();
CategoryController controller = new CategoryController(view);
controller.open();
    }
    
}
