package InventoryManagementSystem.model;

import java.math.BigDecimal; // Import BigDecimal

public class ProductPanelModel {
    private int id;
    private String name;
    private String category;
    private int quantity;
    private BigDecimal price; // Price is now BigDecimal

    // Constructor for adding new products (without ID, as DB generates it)
    public ProductPanelModel(String name, String category, int quantity, double price) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = BigDecimal.valueOf(price); // Convert double to BigDecimal
    }

    // Constructor for fetching from DB (with ID and BigDecimal price)
    public ProductPanelModel(int id, String name, String category, int quantity, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public int getQuantity() { return quantity; }
    public BigDecimal getPrice() { return price; } // Return BigDecimal

    // Setters (if needed for internal model updates, but often not directly by controller)
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(BigDecimal price) { this.price = price; }
}