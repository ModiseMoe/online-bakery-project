
package za.co.mie.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.mie.bakeryDao.BakeryDaoUsersImp;
import za.co.mie.bakeryService.BakeryServiceUsersImp;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.User;

@WebServlet(name = "ViewUsers", urlPatterns = {"/ViewUsers"})
public class usersList extends HttpServlet {
    
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceUsersImp bsci = new BakeryServiceUsersImp(new BakeryDaoUsersImp(con));
        List<User> usersList = null;

        try (PrintWriter out = response.getWriter()) {
            usersList = bsci.getAllUsers();

            if (usersList != null) {
                request.setAttribute("usersList", usersList);
                RequestDispatcher rd = request.getRequestDispatcher("manageUsers.jsp");
                rd.forward(request, response);
            } else {
                out.print("failed to load list");

            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
