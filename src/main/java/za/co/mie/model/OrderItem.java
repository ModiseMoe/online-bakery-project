
package za.co.mie.model;

public class OrderItem {
    private int  product_id;
    private int productQuantity;
    private double unitPrice;
    private String product_title;
    private int order_id;

    public OrderItem() {
    }

    public OrderItem(int product_id, int productQuantity, double unitPrice, String product_title) {
        this.product_id = product_id;
        this.productQuantity = productQuantity;
        this.unitPrice = unitPrice;
        this.product_title = product_title;
    }

    public OrderItem(int product_id, int productQuantity, double unitPrice, String product_title, int order_id) {
        this.product_id = product_id;
        this.productQuantity = productQuantity;
        this.unitPrice = unitPrice;
        this.product_title = product_title;
        this.order_id = order_id;
    }
    

    
    public OrderItem(int product_id, int productQuantity) {
        this.product_id = product_id;
        this.productQuantity = productQuantity;
    }

   
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.product_id;
        hash = 79 * hash + this.productQuantity;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderItem other = (OrderItem) obj;
        if (this.product_id != other.product_id) {
            return false;
        }
        if (this.productQuantity != other.productQuantity) {
            return false;
        }
        return true;
    }

   
    public double getUnitPrice() {
        return unitPrice;
    }

   
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

   
    public String getProduct_title() {
        return product_title;
    }

    
    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    @Override
    public String toString() {
        return "OrderItem{" + "product_id=" + product_id + ", productQuantity=" + productQuantity + ", unitPrice=" + unitPrice + ", product_title=" + product_title + '}';
    }

    /**
     * @return the order_id
     */
    public int getOrder_id() {
        return order_id;
    }

    /**
     * @param order_id the order_id to set
     */
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

   
    
    
    
}
