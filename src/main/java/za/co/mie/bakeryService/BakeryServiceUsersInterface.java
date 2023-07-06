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

public interface BakeryServiceUsersInterface {
    

    public List<User> getAllUsers();

    public User getSingleUser(String email_add);

    public boolean addUser(User user);
    public boolean updateUser(String email_add ,User user);

    public User login(User user);
   public boolean activateUser(String email);
    
    public boolean disableUser(String email);
}
