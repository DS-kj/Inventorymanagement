package InventoryManagementSystem.model;
 
public class ProductModel {
    private int id;
    private String name;
    private CategoryModel category;
    private int quantity;
    private double price;
 
    
    public ProductModel(String name, CategoryModel category, int quantity, double price) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }
 
    
    public ProductModel(int id, String name, CategoryModel category, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }
 
   
    public int getId() {
        return id;
    }
 
    public String getName() {
        return name;
    }
 
    public CategoryModel getCategory() {
        return category;
    }
 
    public int getQuantity() {
        return quantity;
    }
 
    public double getPrice() {
        return price;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public void setCategory(CategoryModel category) {
        this.category = category;
    }
 
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
}