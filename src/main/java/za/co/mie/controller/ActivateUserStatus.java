
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

@WebServlet(name = "ActivateUserStatus", urlPatterns = {"/ActivateUserStatus"})
public class ActivateUserStatus extends HttpServlet {
    
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceUsersImp bspi = new BakeryServiceUsersImp(new BakeryDaoUsersImp(con));
        boolean retVal = false;
        PrintWriter out = response.getWriter();
        String user_emailAdd =request.getParameter("user_emailAdd");
        retVal = bspi.activateUser(user_emailAdd);
        
        if(retVal!= false){

            response.sendRedirect("http://localhost:8080/BakeryProject/ViewUsers");
        }else{
            out.print("failed to activate product");
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
