/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package InventoryManagementSystem;

import InventoryManagementSystem.controller.ProductPanelController;
import InventoryManagementSystem.view.ProductPanel;



/**
 *
 * @author ACER
 */
public class InventoryManagementMain {

    /**
     * @param args the command line arguments
     */
  
    public static void main(String[] args) {
        ProductPanel view = new ProductPanel();
        ProductPanelController controller = new ProductPanelController(view);
        controller.open();
    }
}

//           Category view = new Category();
//CategoryController controller = new CategoryController(view);
//controller.open();
    
    

