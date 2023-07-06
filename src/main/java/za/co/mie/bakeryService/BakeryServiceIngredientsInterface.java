package za.co.mie.bakeryService;

import java.util.List;
import za.co.mie.model.Ingridient;
import za.co.mie.model.Unit;

public interface BakeryServiceIngredientsInterface {

    public boolean addUnit(Unit unit);

    public boolean updateUnit(Unit unit, int unitId);

    public List<Unit> getAllUnits();

    public Unit getSingleUnit(int unitId);

    public boolean addIngredient(Ingridient ingredient, int unitId);

    public boolean updateIngredient(Ingridient ingridient, int ingridientId);

    public List<Ingridient> getAllIngridients();

    public Ingridient getSingleIngridient(int ingridientId);
    
    public String getUnitForIngredient(int ingredient_id);
    
    public List<Ingridient> getAllOutOfStockIngredients();
}
