package InventoryManagementSystem.model;

public class CustomerPanelModel {
    private int id;
    private String name;
    private String Email;
    private String MobileNumber;


    
    public CustomerPanelModel(String name, String Email, String MobileNumber) {
        this.name = name;
        this.Email = Email;
        this.MobileNumber = MobileNumber;
        
    }

    public CustomerPanelModel(int id, String name, String Email, String  MobileNumber) {
        this(name, Email, MobileNumber);
        this.id = id;
    }
    public int getId() { 
        return id; }
    public String getName() {
        return name; }
    public String getEmail() { 
        return Email; }
    public String getMobileNumber() { 
        return MobileNumber; }

    public void setId(int id) { 
        this.id = id; }
    public void setName(String name) { 
        this.name = name; }
    public void setEmail(String Email) {
        this.Email = Email; }
    public void MobileNumber(String MobileNumber) {
        this.MobileNumber = MobileNumber; }
}