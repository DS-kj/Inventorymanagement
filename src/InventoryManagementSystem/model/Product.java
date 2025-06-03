package InventoryManagementSystem.model;

public class Product {
    private int id;
    private String name;
    private String category;
    private int quantity;
    private double price;
    private double rate;

    public Product(int id, String name, String category, int quantity, double price, double rate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.rate = rate;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public double getRate() { return rate; }
}
