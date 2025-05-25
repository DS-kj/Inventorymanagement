/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marksap.model;

/**
 *
 * @author ASUS
 */
public class userdata {
   //private attributes
   //public method
    private String product_name;
    private String Category;
    private Integer Price;
    
    public userdata(String name,String email,String password){
        this.product_name=name;
        this.Category=email;
        this.Price=price;
    }
    //setter
    public void setProductName(String name){
        this.product_name=name;
    }
    public void setCategory(String Category){
        this.Category=Category;
    }
    public void setPrice(String price){
        this.Price=price;
    }
    //getters
    public String getProductName(){
        return this.name;
    }
    public String getCategory(){
        return this.Category;
    }
    public String getPrice(){
        return this.price;
    }
}
