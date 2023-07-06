package za.co.mie.bakeryService;

import java.util.List;
import za.co.mie.model.Recipe;

public interface BakeryServiceRecipeInterface {

    public boolean addRecipe(Recipe recipe);

    public boolean updateRecipe(Recipe recipe, int recipeId);

    public List<Recipe> getAllRecipes();

    public int getRecipeId(String recipeName);

    public boolean addProductToRecipe(int ProductId, int recipeId);

    public Recipe getSingleRecipe(int recipeId);

   public List<String> getIngredientsForRecipe(int recipeId);
   
   public boolean disableRecipe(int recipe_id);
   
   public boolean activateRecipe(int recipe_id);
}
