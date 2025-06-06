/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package InventoryManagementSystem;

import InventoryManagementSystem.controller.AdminPanelController;
import InventoryManagementSystem.view.AdminPanel;

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
         AdminPanel view= new AdminPanel();
        AdminPanelController controller= new AdminPanelController(view);
        controller.open();
    }
    
}
