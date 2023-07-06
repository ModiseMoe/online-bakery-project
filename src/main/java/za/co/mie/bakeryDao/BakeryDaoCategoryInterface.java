
package za.co.mie.bakeryDao;

import java.util.List;
import za.co.mie.model.Category;


public interface BakeryDaoCategoryInterface {
    
     public boolean addCategory(Category category);
     public Category getSingleCategory(int cat_id);
     public boolean updateCategory( int cat_id,String category_name);
     public boolean deleteCategory(Category category);
     public List<Category> getAllCategories();    
     public int getCategoryId(Category category);
     public boolean activateCategory(int cat_id);
     public boolean disableCategory(int cat_id);
      public List<Category> getAllCategoriesByStatus();
}
