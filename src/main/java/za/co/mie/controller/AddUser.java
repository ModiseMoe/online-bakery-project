
package za.co.mie.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;
import za.co.mie.bakeryDao.BakeryDaoUsersImp;
import za.co.mie.bakeryService.BakeryServiceUsersImp;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.Email;
import za.co.mie.model.User;


@WebServlet(name = "AddUser", urlPatterns = {"/AddUser"})
public class AddUser extends HttpServlet {
    

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceUsersImp bsui = new BakeryServiceUsersImp(new BakeryDaoUsersImp(con));
        Email mail = new Email();

        try (PrintWriter out = response.getWriter()) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String hashPwd =  BCrypt.hashpw(password, BCrypt.gensalt());
            String surname = request.getParameter("surname");
            String email = request.getParameter("emailAddr");
            String idNo = request.getParameter("idNo");
            String role = request.getParameter("userRole");
            String mobileNum = request.getParameter("mobileNum");

            User user = new User( username,hashPwd , surname , idNo,role, mobileNum,  email);
            bsui.addUser(user);

            if (user != null) {
                
                try {
                    mail.sendRegistrationEmail(username, email);
                    System.out.println("Email sent succesfully");
                } catch (MessagingException ex) {
                    System.out.println("Error" + ex.getMessage());
                }
                response.sendRedirect("ViewUsers");
            } else {
                out.print("User registration failed");
            }
        }catch(IOException io){
            System.out.println("Error" + io.getMessage());
        }
        
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


}
