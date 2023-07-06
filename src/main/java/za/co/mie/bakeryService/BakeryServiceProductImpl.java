
package za.co.mie.bakeryService;

import java.util.ArrayList;
import java.util.List;
import za.co.mie.bakeryDao.BakeryDaoProductImpl;
import za.co.mie.model.CartLineItem;
import za.co.mie.model.Category;
import za.co.mie.model.Product;


public class BakeryServiceProductImpl implements BakeryServiceProductInterface {
    private BakeryServiceProductInterface bakeryDaoProductInterface;
    private BakeryDaoProductImpl bdpi;

    public BakeryServiceProductImpl(BakeryDaoProductImpl bdpi) {
        this.bdpi = bdpi;
    }
    
    

   @Override
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList() ;
        return list == null ? null : bdpi.getAllProducts();
    }
    
    @Override
     public List<Product> getAllProductsByStatus(){
         List<Product> list = new ArrayList();
         return list == null ? null : bdpi.getAllProductsByStatus();
     }
            
    @Override
    public List<Product> getAllProductsFromCategory(String category_name) {
        List<Category> list = new ArrayList() ;
        return list == null ? null : bdpi.getAllProductsFromCategory(category_name);
    }

    @Override
    public boolean addNewProduct(Product product , int recipe_id) {
       return product == null && recipe_id < 0 ? false : bdpi.addNewProduct(product, recipe_id);
    }

    @Override
    public boolean addProductToCategory(int productId, int cat_id) {
                return productId > 0 ? bdpi.addProductToCategory(productId, cat_id):false
                && cat_id > 0 ? bdpi.addProductToCategory(productId, cat_id) : false;
    }

    @Override
    public boolean updateProduct(int product_id, Product product1) {
        return product_id < 0 ? false : bdpi.updateProduct(product_id, product1)
                && product1 != null ? bdpi.updateProduct(product_id, product1) : null;
    }

//    @Override
//    public boolean deleteProduct(int productId) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public int getProductId(String productName) {
         return productName == null ? 0 : bdpi.getProductId(productName);
    }

    @Override
    public Product getSingleProduct(int product_id) {
        return product_id < 0 ? null : bdpi.getSingleProduct(product_id);
    }

    @Override
    public boolean deleteProduct(int productId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean activateProduct(int product_id) {
       return product_id < 0  ? false : bdpi.activateProduct(product_id);
    }

    @Override
    public boolean disableProduct(int product_id) {
       return product_id < 0 ? false : bdpi.disableProduct(product_id);
    }

    @Override
    public List<CartLineItem> getCartProducts(List<CartLineItem> cart) {
       return cart == null ? null : bdpi.getCartProducts(cart);
    }

    @Override
    public double getTotalCartPrice(List<CartLineItem> cart) {
        return cart == null ? null : bdpi.getTotalCartPrice(cart);
    }

    @Override
    public String getCategoryForProduct(int product_id) {
       return product_id < 0 ? null : bdpi.getCategoryForProduct(product_id);
    }

    @Override
    public List<Product> getProductsbyCategory(int cat_id) {
       return cat_id <0 ?null :bdpi.getProductsbyCategory(cat_id);
    }

    @Override
    public List<Product> performSearch(String product_title) {
        return product_title == null ? null : bdpi.performSearch(product_title);
    }
    
    
      
    
}
