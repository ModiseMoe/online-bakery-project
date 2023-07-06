package za.co.mie.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class Order {

    private Date orderDate;
    private int productQuantity;
    private String userId;
    private int product_id;
    private List<OrderItem> orderItems;
    private int order_id;
    private boolean payment_status;
    private boolean status;

    public Order() {
    }

    public Order(Date orderDate, String userId) {
        this.orderDate = orderDate;
        this.userId = userId;
    }

    public Order(Date orderDate, String userId, int order_id) {
        this.orderDate = orderDate;
        this.userId = userId;
        this.order_id = order_id;
    }

    public Order(Date orderDate, String userId, int order_id, boolean payment_status , boolean status) {
        this.orderDate = orderDate;
        this.userId = userId;
        this.order_id = order_id;
        this.payment_status = payment_status;
        this.status = status;
    }

    public Order(Date orderDate, String userId, boolean payment_status, boolean status) {
        this.orderDate = orderDate;
        this.userId = userId;
        this.payment_status = payment_status;
        this.status = status;
    }
    
    
   

       
    public Order(Date orderDate, int productQuantity, String userId, int product_id) {
        this.orderDate = orderDate;
        this.productQuantity = productQuantity;
        this.userId = userId;
        this.product_id = product_id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.orderDate);
        hash = 97 * hash + this.productQuantity;
        hash = 97 * hash + Objects.hashCode(this.userId);
        hash = 97 * hash + this.product_id;
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
        final Order other = (Order) obj;
        if (this.productQuantity != other.productQuantity) {
            return false;
        }
        if (this.product_id != other.product_id) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.orderDate, other.orderDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "orderDate=" + orderDate + ", productQuantity=" + productQuantity + ", userId=" + userId + ", product_id=" + product_id + ", orderItems=" + orderItems + ", order_id=" + order_id + ", payment_status=" + payment_status + '}';
    }

    

    /**
     * @return the orderItems
     */
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    /**
     * @param orderItems the orderItems to set
     */
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
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

    /**
     * @return the payment_status
     */
    public boolean isPayment_status() {
        return payment_status;
    }

    /**
     * @param payment_status the payment_status to set
     */
    public void setPayment_status(boolean payment_status) {
        this.payment_status = payment_status;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

}
