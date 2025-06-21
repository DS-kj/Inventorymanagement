package InventoryManagementSystem.model;

public class DashboardModel {
    private int id;
    private String productName;
    private String category;
    private int quantity;
    private double price;
    private double rate;

    public DashboardModel() {}

    public DashboardModel(int id, String productName, String category, int quantity, double price, double rate) {
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.rate = rate;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getRate() { return rate; }
    public void setRate(double rate) { this.rate = rate; }
}
