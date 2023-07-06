package za.co.mie.bakeryDao;

import java.time.LocalDate;
import za.co.mie.model.*;
import java.util.List;

public interface BakeryDaoUsersInterface {

    public List<User> getAllUsers();

    public User getSingleUser(String email_add);

    public boolean addUser(User user);

    public boolean updateUser(String email_add, User user);

    public User login(User user);

    public boolean activateUser(String recipe_id);

    public boolean disableUser(String recipe_id);
}
