package za.co.mie.bakeryDao;

import java.util.List;
import za.co.mie.model.Allergy;
import za.co.mie.model.Ingridient;
import za.co.mie.model.Recipe;

public interface BakeryDaoIngredientInterface {

    public boolean addIngridient(Ingridient ingridient);

    public boolean addRecipe(Recipe recipe, List<Ingridient> ingredients);

    public List<Ingridient> getAllIngridients();

    public List<Ingridient> getAllIngridientsForRecipe(int recipeId);

    public List<Recipe> getAllRecipes();

    public List<Recipe> getAllRecipesForCategory(int categoryId);

    public boolean addAllergy(Allergy allergy);

    public Allergy getAllergy(int id);

    public List<Allergy> getAllAllergy();
}
