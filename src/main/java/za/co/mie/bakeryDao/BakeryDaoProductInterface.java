package za.co.mie.bakeryDao;

import java.util.List;
import za.co.mie.model.CartLineItem;
import za.co.mie.model.Category;
import za.co.mie.model.Product;

public interface BakeryDaoProductInterface {

    public List<Product> getAllProducts();
    
     public List<Product> getAllProductsByStatus();
  

    public List<Product> getAllProductsFromCategory(String category_name);

    public Product getSingleProduct(int product_id);

    public boolean addNewProduct(Product product , int recipe_id);

    public boolean addProductToCategory(int productId, int cat_id);

    public boolean updateProduct(int product_id, Product product1);

    public boolean deleteProduct(int productId);

    public int getProductId(String product_ttile);

    public boolean activateProduct(int product_id);

    public boolean disableProduct(int product_id);

    public List<CartLineItem> getCartProducts(List<CartLineItem> cart);

    public double getTotalCartPrice(List<CartLineItem> cart);
    public String getCategoryForProduct( int product_id);
    public List<Product> getProductsbyCategory(int cat_id);
    public List<Product> performSearch( String product_title);
}
