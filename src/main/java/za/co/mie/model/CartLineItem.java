/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.mie.model;

/**
 *
 * @author mdice
 */
public class CartLineItem {
    private Product product;
    private int quantity;

    public CartLineItem() {
    }

    
    public CartLineItem(String productTitle, int quantity) {
        product = new Product(productTitle);
        this.quantity=quantity;
    }
    
    public CartLineItem(int id, int quantity) {
        product = new Product(id);
        this.quantity=quantity;
    }
    
    public CartLineItem(Product product, int quantity) {
        this.product=product;
        this.quantity=quantity;
    }

    public Product getProduct() {
        return product;
    }

   
    public void setProduct(Product product) {
        this.product = product;
    }

    
    public int getQuantity() {
        return quantity;
    }

    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartLineItem{" + "product=" + product + ", quantity=" + quantity + '}';
    }

    
    
}
