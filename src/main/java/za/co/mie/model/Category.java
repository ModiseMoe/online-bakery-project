
package za.co.mie.model;

import java.util.Objects;

public class Category {
    private String categoryName;
    private boolean status;
    private int id;

    public Category() {
    }

    public Category(String categoryName, int id) {
        this.categoryName = categoryName;
        this.id = id;
    }

    
    public Category(String categoryName, boolean status, int id) {
        this.categoryName = categoryName;
        this.status = status;
        this.id = id;
    }

    
     public Category(String categoryName , boolean status) {
        this.categoryName = categoryName;
        this.status = status;
    }
    
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.categoryName);
        hash = 11 * hash + (this.status ? 1 : 0);
        hash = 11 * hash + this.id;
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
        final Category other = (Category) obj;
        if (this.status != other.status) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.categoryName, other.categoryName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Category{" + "categoryName=" + categoryName + ", status=" + status + ", id=" + id + '}';
    }

    
    

   
  
    public boolean getStatus() {
        return status;
    }

   
    public void setStatus(boolean Status) {
        this.status = Status;
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
    
}
