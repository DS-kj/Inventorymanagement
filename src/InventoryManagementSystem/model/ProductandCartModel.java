package InventoryManagementSystem.model;

public class ProductAndCartModel {
    private final int id;
    private final String name;
    private final String category;
    private int quantity;
    private final double price;

    public ProductAndCartModel(int id, String name, String category, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}