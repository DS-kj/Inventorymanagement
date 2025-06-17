package InventoryManagementSystem.model;
 
public class OrderModel {
    private int orderId;
    private String productName;
    private int quantity;
    private double price;
    private String orderDate;
 
    public OrderModel(int orderId, String productName, int quantity, double price, String orderDate) {
        this.orderId = orderId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.orderDate = orderDate;
    }
 
    // Getters
    public int getOrderId() {
        return orderId;
    }
 
    public String getProductName() {
        return productName;
    }
 
    public int getQuantity() {
        return quantity;
    }
 
    public double getPrice() {
        return price;
    }
 
    public String getOrderDate() {
        return orderDate;
    }
}