/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package InventoryManagementSystem;

import InventoryManagementSystem.DAO.CategoryDao;
import InventoryManagementSystem.controller.AdminPanelController;
import InventoryManagementSystem.controller.CategoryController;
import InventoryManagementSystem.controller.CustomerPanelController;
import InventoryManagementSystem.controller.LoginController;
import InventoryManagementSystem.controller.ProductAndCartController;
import InventoryManagementSystem.view.AdminPanel;
//import InventoryManagementSystem.view.AdminPanel;
import InventoryManagementSystem.view.Category;
import InventoryManagementSystem.view.CustomerPanel;
import InventoryManagementSystem.view.LoginPanel;
import InventoryManagementSystem.view.ProductandCart;


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
//        AdminPanel view = new AdminPanel();
//        AdminPanelController controller= new AdminPanelController(view);
//        controller.open();
        
//         CustomerPanel view = new CustomerPanel();
//         CustomerPanelController controller = new CustomerPanelController(view);
//         controller.open();
      
        LoginPanel view = new LoginPanel();
        LoginController controller = new LoginController(view);
        controller.open();
       
        
//           Category view = new Category();
//CategoryController controller = new CategoryController(view);
//controller.open();
    }
    
}
