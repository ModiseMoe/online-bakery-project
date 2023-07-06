
package za.co.mie.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.mie.bakeryDao.BakeryDaoUsersImp;
import za.co.mie.bakeryService.BakeryServiceUsersImp;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.User;


@WebServlet(name = "EditUser", urlPatterns = {"/EditUser"})
public class EditUser extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
          BakeryServiceUsersImp bsui = new BakeryServiceUsersImp(new BakeryDaoUsersImp(con));
          PrintWriter out = response.getWriter();
          String user_emailAdd = request.getParameter("user_emailAdd");
          User user = bsui.getSingleUser(user_emailAdd);
         
          
          if(user != null){
               request.setAttribute("userData", user);
              request.getRequestDispatcher("updateUserForm.jsp").forward(request, response);
          }else{
              out.print("no users found");
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
