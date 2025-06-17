/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package InventoryManagementSystem;

import InventoryManagementSystem.DAO.CategoryDao;
import InventoryManagementSystem.controller.AdminPanelController;
import InventoryManagementSystem.controller.CategoryController;
import InventoryManagementSystem.controller.CustomerchooserController;
import InventoryManagementSystem.controller.ProductAndCartController;
import InventoryManagementSystem.controller.ViewOrdersController;
import InventoryManagementSystem.view.AdminPanel;
//import InventoryManagementSystem.view.AdminPanel;
import InventoryManagementSystem.view.Category;
import InventoryManagementSystem.view.Customerchooser;
import InventoryManagementSystem.view.ProductandCart;
import InventoryManagementSystem.view.ViewOrders;


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
//         Customerchooser view = new Customerchooser();
//         CustomerchooserController controller= new CustomerchooserController(view);
//         controller.open();
//        ProductandCart view = new ProductandCart();
//        ProductAndCartController controller= new ProductAndCartController(view);
//        controller.open();
        ViewOrders view = new ViewOrders();
         ViewOrdersController controller= new ViewOrdersController(view);
         controller.open();
//           Category view = new Category();
//CategoryController controller = new CategoryController(view);
//controller.open();
    }
    
}
