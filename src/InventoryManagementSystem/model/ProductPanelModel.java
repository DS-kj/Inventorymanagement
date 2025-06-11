package InventoryManagementSystem.model;

public class ProductPanelModel {
    private int id;
    private String name;
    private String category;
    private int quantity;
    private double price;

    // Constructor
    public ProductPanelModel(String name, String category, int quantity, double price) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public ProductPanelModel(int id, String name, String category, int quantity, double price) {
        this(name, category, quantity, price);
        this.id = id;
    }

    // Getters and Setters
    public int getId() { 
        return id; }
    public String getName() {
        return name; }
    public String getCategory() { 
        return category; }
    public int getQuantity() { 
        return quantity; }
    public double getPrice() { 
        return price; }

    public void setId(int id) { 
        this.id = id; }
    public void setName(String name) { 
        this.name = name; }
    public void setCategory(String category) {
        this.category = category; }
    public void setQuantity(int quantity) {
        this.quantity = quantity; }
    public void setPrice(double price) { 
        this.price = price; }
}
