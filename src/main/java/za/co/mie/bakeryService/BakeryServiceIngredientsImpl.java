package za.co.mie.bakeryService;

import java.util.ArrayList;
import java.util.List;
import za.co.mie.bakeryDao.BakeryDaoIngredientsImpl;
import za.co.mie.model.Ingridient;
import za.co.mie.model.Unit;

public class BakeryServiceIngredientsImpl implements BakeryServiceIngredientsInterface {

    private BakeryDaoIngredientsImpl bdci;

    public BakeryServiceIngredientsImpl(BakeryDaoIngredientsImpl bdci) {
        this.bdci = bdci;
    }

    @Override
    public boolean addUnit(Unit unit) {
        return unit == null ? false : bdci.addUnit(unit);
    }

    @Override
    public boolean updateUnit(Unit unit, int unitId) {
        return unitId > 0 ? bdci.updateUnit(unit, unitId) : false
                && unit != null ? bdci.updateUnit(unit, unitId) : null;
    }

    @Override
    public List<Unit> getAllUnits() {
        List<Unit> list = new ArrayList();
        return list == null ? null : bdci.getAllUnits();
    }

    @Override
    public Unit getSingleUnit(int unitId) {
        return unitId < 0 ? null : bdci.getSingleUnit(unitId);
    }

    @Override
    public boolean addIngredient(Ingridient ingredient, int unitId) {
        return ingredient == null && unitId < 0 ? false : bdci.addIngredient(ingredient, unitId);

    }

    @Override
    public boolean updateIngredient(Ingridient ingridient, int ingridientId) {
        return ingridientId > 0 && ingridient != null ? bdci.updateIngredient(ingridient, ingridientId) : false;

    }

    @Override
    public List<Ingridient> getAllIngridients() {
        List<Ingridient> list = new ArrayList();
        return list == null ? null : bdci.getAllIngridients();
    }

    @Override
    public Ingridient getSingleIngridient(int ingridientId) {
        return ingridientId < 0 ? null : bdci.getSingleIngridient(ingridientId);
    }

    @Override
    public String getUnitForIngredient(int ingredient_id) {
        return ingredient_id < 0 ? null : bdci.getUnitForIngredient(ingredient_id);
    }

    @Override
    public List<Ingridient> getAllOutOfStockIngredients() {
        List<Ingridient> list = new ArrayList();
        return list == null ? null : bdci.getAllOutOfStockIngredients();
    }

}
