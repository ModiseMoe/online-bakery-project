package za.co.mie.model;

import java.util.Objects;

public class Product {

    private String product_title;
    private String description;
    private String productWarnigs;
    private double productPrice;
    private boolean status;
    private int id;
    private String image;

    public Product(String product_title, String description, String productWarnigs, double productPrice, boolean status, int id ,String image) {
        this.product_title = product_title;
        this.description = description;
        this.productWarnigs = productWarnigs;
        this.productPrice = productPrice;
        this.status = status;
        this.id = id;
        this.image = image;
    }
    

    public Product(String product_title, String description, String productWarnigs, double productPrice, String image) {
        this.product_title = product_title;
        this.description = description;
        this.productWarnigs = productWarnigs;
        this.productPrice = productPrice;
        this.image = image;
    }

    
    public Product(String product_title, String description, String productWarnigs, double productPrice) {
        this.product_title = product_title;
        this.description = description;
        this.productWarnigs = productWarnigs;
        this.productPrice = productPrice;
    }

    public Product(String product_title, String description, String productWarnigs, double productPrice, boolean status , String image) {
        this.product_title = product_title;
        this.description = description;
        this.productWarnigs = productWarnigs;
        this.productPrice = productPrice;
        this.status = status;
        this.image = image;
    }

    public Product(String product_title) {
        this.product_title = product_title;
    }

    public Product(int id) {
        this.id = id;
    }
    
    

    public Product(String product_title, boolean status) {
        this.product_title = product_title;
        this.status = status;
    }

    public Product() {
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductWarnigs() {
        return productWarnigs;
    }

    public void setProductWarnigs(String productWarnigs) {
        this.productWarnigs = productWarnigs;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Product{" + "product_title=" + product_title + ", description=" + description + ", productWarnigs=" + productWarnigs + ", productPrice=" + productPrice + ", status=" + status + ", id=" + id + ", image=" + image + '}';
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.product_title);
        hash = 97 * hash + (this.status ? 1 : 0);
        hash = 97 * hash + this.getId();
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
        final Product other = (Product) obj;
        if (this.status != other.status) {
            return false;
        }
        if (this.getId() != other.getId()) {
            return false;
        }
        if (!Objects.equals(this.product_title, other.product_title)) {
            return false;
        }
        return true;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

}
