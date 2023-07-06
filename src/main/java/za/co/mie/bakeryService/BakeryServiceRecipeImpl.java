package za.co.mie.bakeryService;

import java.util.ArrayList;
import java.util.List;
import za.co.mie.bakeryDao.BakeryDaoRecipeImpl;
import za.co.mie.model.Recipe;

public class BakeryServiceRecipeImpl implements BakeryServiceRecipeInterface {

    private BakeryDaoRecipeImpl bdci;
    
        public BakeryServiceRecipeImpl(BakeryDaoRecipeImpl bdci) {
        this.bdci = bdci;
    }

    @Override
    public boolean addRecipe(Recipe recipe) {
        return recipe == null ? false : bdci.addRecipe(recipe);
    }

    @Override
    public boolean updateRecipe(Recipe recipe, int recipeId) {
        return recipe == null ? false : bdci.updateRecipe(recipe, recipeId)
                && recipeId > 0 ? bdci.updateRecipe(recipe, recipeId) : false;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        List<Recipe> list = new ArrayList();
        return list == null ? null : bdci.getAllRecipes();
    }

    @Override
    public int getRecipeId(String recipeName) {
        return recipeName == null ? 0 : bdci.getRecipeId(recipeName);
    }

    @Override
    public boolean addProductToRecipe(int productId, int recipeId) {
        return productId > 0 ? bdci.addProductToRecipe(productId, recipeId): false
                && recipeId > 0 ? bdci.addProductToRecipe(productId, recipeId) : false;
    }

    @Override
    public Recipe getSingleRecipe(int recipeId) {
        return recipeId < 0 ? null : bdci.getSingleRecipe(recipeId);
    }

    @Override
     public List<String> getIngredientsForRecipe(int recipeId) {
        return recipeId < 0 ? null : bdci.getIngredientsForRecipe(recipeId);
    }

    @Override
    public boolean disableRecipe(int recipe_id) {
        return recipe_id < 0 ? false : bdci.disableRecipe(recipe_id);
    }

    @Override
    public boolean activateRecipe(int recipe_id) {
        return recipe_id < 0 ? false : bdci.activateRecipe(recipe_id);
    }

}
