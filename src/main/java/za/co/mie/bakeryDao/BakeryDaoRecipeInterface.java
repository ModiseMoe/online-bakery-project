
package za.co.mie.bakeryDao;

import java.util.List;
import za.co.mie.model.Recipe;


public interface BakeryDaoRecipeInterface {
    public boolean addRecipe(Recipe recipe);
    public boolean updateRecipe(Recipe recipe,int recipeId);
    public List<Recipe> getAllRecipes();
    public int getRecipeId(String recipeName);
    public boolean addProductToRecipe(int ProductId,int recipeId);
    public Recipe getSingleRecipe(int recipeId);
    public List<String> getIngredientsForRecipe(int recipeId);
    public boolean activateRecipe(int recipe_id);
    public boolean disableRecipe(int recipe_id);
}
