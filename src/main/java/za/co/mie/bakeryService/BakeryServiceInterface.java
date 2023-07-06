
package za.co.mie.bakeryService;

import java.util.List;
import za.co.mie.model.Address;
import za.co.mie.model.Allergy;
import za.co.mie.model.Category;
import za.co.mie.model.Ingridient;
import za.co.mie.model.Order;
import za.co.mie.model.Product;
import za.co.mie.model.Recipe;
import za.co.mie.model.User;


public interface BakeryServiceInterface {
   //  
//   //*******************************************
//   public List<Product> getAllProducts();
//   public List<Product> getAllProductsFromCategory(int categoryId);
//   public Product getSingleProduct(int productId);
//   public List<Category> getAllCategory();
//   public Category getSingleCategory(int categoryId);
//   public boolean addNewProduct(Product product, int categoryId);
//   public boolean updateProduct(Product product);
//   public boolean deleteProduct(int productId);
//   //************************************************
   
   
    public List<User> getAllUsers();
    public User getSingleUser(int emailAddId);
    public boolean addUser(User user);
    public boolean addAddress(Address address);
    boolean addAddressToUser(String userId, int addressId);
    boolean addUserToExistingAddress(User user, int addressId);
    boolean addAddressToExistingUser(Address address, String userId);
    public boolean updateUser(User user);
    public boolean login(User user);
   //********************************************
   
//   
//   //***********************************************
//   public List<Order> getAllOrdersForUser(int userId);
//   public List<Order> getAllOutstandingOrdersForUser(int userId);
//   public List<Order> getAllOrders(LocalDate fromDate);
//   public List<Order> getAllOrders(LocalDate fromDate, LocalDate toDate);
//   public List<Order> getAllProceesedOrders(LocalDate fromDate, LocalDate toDate);
//   public List<Order> getAllOutstandingOrders(LocalDate fromDate, LocalDate toDate);
//   public Order getSingleOrder(int id);
//   public boolean placeOrder(Order order);
//   public boolean removeOrder(int orderId);
//   
//   
//   //************************************************
//   public boolean addIngridient(Ingridient ingridient);
//   public boolean addRecipe(Recipe recipe, List<Ingridient> ingredients);
//   public List<Ingridient> getAllIngridients();
//   public List<Ingridient> getAllIngridientsForRecipe(int recipeId);
//   public List<Recipe> getAllRecipes();
//   public List<Recipe> getAllRecipesForCategory(int categoryId);
//   //***************************************************
//    
//   public boolean addAllergy(Allergy allergy);
//   public Allergy getAllergy(int id);
//   public List<Allergy> getAllAllergy();
   
   
   
   
   
}
