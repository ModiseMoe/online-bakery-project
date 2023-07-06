
package za.co.mie.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;
import za.co.mie.bakeryDao.BakeryDaoUsersImp;
import za.co.mie.bakeryService.BakeryServiceUsersImp;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.User;


@WebServlet(name = "UpdateUser", urlPatterns = {"/UpdateUser"})
public class UpdateUser extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
          BakeryServiceUsersImp bsui = new BakeryServiceUsersImp(new BakeryDaoUsersImp(con));
          User auth = (User) request.getSession().getAttribute("auth");
          
           boolean retVal = false;
           
           try(PrintWriter out = response.getWriter()){
               
               String userId = request.getParameter("user_emailAdd");
                String username = request.getParameter("user_name");
                    String password = request.getParameter("user_pwd");
                    String hashPwd =  BCrypt.hashpw(password, BCrypt.gensalt());
                    String surname = request.getParameter("user_surname");
                    String email = request.getParameter("user_emailAdd");
                    String idNo = request.getParameter("user_idNo");
                    String mobileNum = request.getParameter("user_mobileNum");
                    retVal = bsui.updateUser(userId, new User(username, surname, hashPwd , email,idNo,mobileNum));
                    
                    if(retVal != false && auth.getUserRole().equals("User")){
                        request.getRequestDispatcher("indexPageList").forward(request, response);
                    }else if(retVal != false && auth.getUserRole().equals("Admin")){
                        request.getRequestDispatcher("AdminLandingPage.jsp");
                    }else{
                        out.print("update faled");
                    }
                           
                    
                    
                    
                    
               
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
