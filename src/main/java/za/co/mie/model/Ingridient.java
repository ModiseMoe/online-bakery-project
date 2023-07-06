package za.co.mie.model;

import java.util.Objects;

public class Ingridient {

    private String ingridientsName;
    private int quantityOnHand, minimumStockQuantity, ingredientId, unitId;

    public Ingridient() {
    }

    public Ingridient(String ingridientsName, int quantityOnHand, int minimumStockQuantity, int ingredientId, int unitId) {
        this.ingridientsName = ingridientsName;
        this.quantityOnHand = quantityOnHand;
        this.minimumStockQuantity = minimumStockQuantity;
        this.ingredientId = ingredientId;
        this.unitId = unitId;
    }

    public Ingridient(String ingridientsName, int quantityOnHand, int minimumStockQuantity) {
        this.ingridientsName = ingridientsName;
        this.minimumStockQuantity = minimumStockQuantity;
        this.quantityOnHand = quantityOnHand ;
    }

    public Ingridient(String ingridientsName, int minimumStockQuantity) {
        this.ingridientsName = ingridientsName;
        this.minimumStockQuantity = minimumStockQuantity;
    }

    public Ingridient(String ingridientsName) {
        this.ingridientsName = ingridientsName;
    }

    public Ingridient(int minimumStockQuantity, int ingredientId) {
        this.minimumStockQuantity = minimumStockQuantity;
        this.ingredientId = ingredientId;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.ingridientsName);
        hash = 79 * hash + this.quantityOnHand;
        hash = 79 * hash + this.minimumStockQuantity;
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
        final Ingridient other = (Ingridient) obj;
        if (this.quantityOnHand != other.quantityOnHand) {
            return false;
        }
        if (this.minimumStockQuantity != other.minimumStockQuantity) {
            return false;
        }
        if (!Objects.equals(this.ingridientsName, other.ingridientsName)) {
            return false;
        }
        return true;
    }

 

    public String getIngridientsName() {
        return ingridientsName;
    }

    public void setIngridientsName(String ingridientsName) {
        this.ingridientsName = ingridientsName;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    public int getMinimumStockQuantity() {
        return minimumStockQuantity;
    }

    public void setMinimumStockQuantity(int minimumStockQuantity) {
        this.minimumStockQuantity = minimumStockQuantity;
    }

    @Override
    public String toString() {
        return "Ingridient{" + "ingridientsName=" + ingridientsName + ", quantityOnHand=" + quantityOnHand + ", minimumStockQuantity=" + minimumStockQuantity + ", ingredientId=" + ingredientId + ", unitId=" + unitId + '}';
    }
    
    

}
