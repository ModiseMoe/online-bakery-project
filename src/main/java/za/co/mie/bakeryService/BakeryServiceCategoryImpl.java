
package za.co.mie.bakeryService;

import java.util.ArrayList;
import java.util.List;
import za.co.mie.bakeryDao.BakeryDaoCategoryImpl;
import za.co.mie.bakeryDao.BakeryDaoCategoryInterface;
import za.co.mie.model.Category;


public class BakeryServiceCategoryImpl implements BakeryServiceCategoryInterface{
    private BakeryDaoCategoryInterface bakeryDaoCategoryInterface;
    private  BakeryDaoCategoryImpl bdci;

    public BakeryServiceCategoryImpl(BakeryDaoCategoryImpl bdci) {
        this.bdci = bdci;
    }
    
     public Category getSingleCategory(int cat_id){
         return cat_id < 0 ? null : bdci.getSingleCategory(cat_id);
     }
    
     @Override
    public boolean addCategory(Category category) {
         return category == null ? false : bdci.addCategory(category);
    }

    @Override
    public boolean updateCategory(int cat_id, String category_name) {
        return cat_id > 0 ?bdci.updateCategory(cat_id, category_name) : false
                && category_name != null ? bdci.updateCategory(cat_id , category_name) : null;
    }

    @Override
    public boolean deleteCategory(Category category) {
         return category == null ? false : bdci.addCategory(category);
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList() ;
        return list == null ? null : bdci.getAllCategories();
    }
    
    @Override
    public List<Category> getAllCategoriesByStatus() {
        List<Category> list = new ArrayList() ;
        return list == null ? null : bdci.getAllCategoriesByStatus();
    }

    @Override
    public int getCategoryId(Category category) {
        return category == null ? 0 : bdci.getCategoryId(category);
    }

    @Override
    public boolean activateCategory(int cat_id) {
        return cat_id < 0 ? false : bdci.activateCategory(cat_id);
    }

    @Override
    public boolean disableCategory(int cat_id) {
       return  cat_id < 0 ? false : bdci.disableCategory(cat_id);
    }
    
}
