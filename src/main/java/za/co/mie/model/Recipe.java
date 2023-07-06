package za.co.mie.model;

import java.util.List;
import java.util.Objects;

public class Recipe {

    private String recipeName;
    private String description;
    private int recipeId;
    private boolean status;
    private List<Ingridient> ingridients;
    private int ingrQuantity;

    public Recipe(String recipeName) {
        this(recipeName, "");
    }

    public Recipe(String recipeName, String description, int recipeId) {
        this.recipeName = recipeName;
        this.description = description;
        this.recipeId = recipeId;
    }

    public Recipe(String recipeName, String description, int recipeId, boolean status, List<Ingridient> ingridients, int ingrQuantity) {
        this.recipeName = recipeName;
        this.description = description;
        this.recipeId = recipeId;
        this.status = status;
        this.ingridients = ingridients;
        this.ingrQuantity = ingrQuantity;
    }
    
    

    public Recipe(String recipeName, String description, int recipeId, boolean status) {
        this.recipeName = recipeName;
        this.description = description;
        this.recipeId = recipeId;
        this.status = status;
    }
    
    

    public Recipe(String recipeName, String description, int recipeId, boolean status, List<Ingridient> ingridients) {
        this.recipeName = recipeName;
        this.description = description;
        this.recipeId = recipeId;
        this.status = status;
        this.ingridients = ingridients;
    }
    
    

    public Recipe(String recipeName, String description, int recipeId, List<Ingridient> ingridients) {
        this.recipeName = recipeName;
        this.description = description;
        this.recipeId = recipeId;
        this.ingridients = ingridients;
    }

    public Recipe(String recipeName, String description, List<Ingridient> ingridients) {
        this.recipeName = recipeName;
        this.description = description;
        this.ingridients = ingridients;
    }

    public Recipe(String recipeName, String description, List<Ingridient> ingridients, int ingrQuantity) {
        this.recipeName = recipeName;
        this.description = description;
        this.ingridients = ingridients;
        this.ingrQuantity = ingrQuantity;
    }
    
    
    
    

    public Recipe(String recipeName, String description) {
        this.recipeName = recipeName;
        this.description = description;
    }

    public Recipe() {
        this("Unknown", "");
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.getRecipeName());
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
        final Recipe other = (Recipe) obj;
        if (!Objects.equals(this.recipeName, other.recipeName)) {
            return false;
        }
        return true;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public List<Ingridient> getIngridients() {
        return ingridients;
    }


    public void setIngridients(List<Ingridient> ingridients) {
        this.ingridients = ingridients;
    }

    @Override
    public String toString() {
        return "Recipe{" + "recipeName=" + recipeName + ", description=" + description + ", recipeId=" + recipeId + ", status=" + status + '}';
    }

    public int getIngrQuantity() {
        return ingrQuantity;
    }

    public void setIngrQuantity(int ingrQuantity) {
        this.ingrQuantity = ingrQuantity;
    }
    
    

    public int getRecipeId() {
        return recipeId;
    }

    public boolean getStatus() {
        return status;
    }
    
    
    

}
