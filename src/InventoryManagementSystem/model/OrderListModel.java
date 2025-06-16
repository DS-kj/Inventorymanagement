/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InventoryManagementSystem.model;

public class OrderListModel {
    private int Order_ID;
    private String Order_date;
    private long Total_Paid;

    public OrderListModel(int Order_ID, String Order_date, long Total_Paid) {
        this.Order_ID = Order_ID;
        this.Order_date = Order_date;
        this.Total_Paid = Total_Paid;
    }

    // Getters
    public int getOrderId() { 
        return Order_ID; 
    }
    
    public String getOrderDate() { 
        return Order_date; 
    }
    
    public long getTotalPaid() { 
        return Total_Paid; 
    }
}

