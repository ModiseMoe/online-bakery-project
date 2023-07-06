package za.co.mie.bakeryService;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.mie.bakeryDao.BakeryDaoUsersImp;
import za.co.mie.bakeryDao.BakeryDaoUsersInterface;
import za.co.mie.controller.ProcessRequest;
import za.co.mie.model.User;

public class BakeryServiceUsersImp implements BakeryServiceUsersInterface, ProcessRequest {

    private BakeryDaoUsersInterface bakeryDaoUsersInterface;
    private String ourView;
    private BakeryDaoUsersImp bdi;

    public BakeryServiceUsersImp(BakeryDaoUsersImp bdi) {
        this.bdi = bdi;
    }

    @Override
    public List<User> getAllUsers() {
       List<User> list = new ArrayList() ;
        return list == null ? null : bdi.getAllUsers();
    }

    @Override
    public User getSingleUser(String email_add) {
        return email_add == null ? null : bdi.getSingleUser(email_add);
    }

    @Override
    public boolean addUser(User user) {
        return user == null ? false : bdi.addUser(user);
    }

    @Override
    public boolean updateUser(String email_add, User user) {
        return email_add == null ? false : bdi.updateUser(email_add, user)
                && user == null ? false : bdi.updateUser(email_add, user);
    }

    @Override
    public User login(User user) {
        return user == null ? null : bdi.login(user);
    }

    @Override
    public boolean activateUser(String email) {
        return email == null ? false : bdi.activateUser(email);
    }

    @Override
    public boolean disableUser(String email) {
        return email == null ? false : bdi.disableUser(email);
    }
// ******************************************************************************************************************  

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {
//       
//                    String username = request.getParameter("username");
//                    String password = request.getParameter("password");
//                    String surname = request.getParameter("surname");
//                    String email = request.getParameter("emailAddr");
//                    String idNo = request.getParameter("idNo");
//                    String role = request.getParameter("userRole");
//                    String mobileNum = request.getParameter("mobileNum");
//
//                    if (email != null && !email.isEmpty()) {
//                        email = email.trim();
//                    }
//                    if (!email.isEmpty()) {
//                        if (addUser(new User(username, surname, password, email, idNo, role, mobileNum))) {
//                            request.setAttribute("ans", "User registered succesfully");
//                        } else {
//                            request.setAttribute("ans", "User Registration failed");
//                        }
//                    }
//                    ourView = "registration.jsp";
//                    
    }

    @Override
    public void processTheResponse(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ourView);
//        try {
//            requestDispatcher.forward(request, response);
//            response.sendRedirect("login.jsp");
//        } catch (ServletException | IOException ex) {
//            System.out.println("Error Dispatching view: " + ex.getMessage());
//        }

    }

}
