package za.co.mie.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.mie.bakeryDao.BakeryDaoRecipeImpl;
import za.co.mie.bakeryService.BakeryServiceRecipeImpl;
import za.co.mie.db.listener.DBManager;

@WebServlet(name = "DisableRecipeStatus", urlPatterns = {"/DisableRecipeStatus"})
public class DisableRecipeStatus extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceRecipeImpl bsci = new BakeryServiceRecipeImpl(new BakeryDaoRecipeImpl(con));

        boolean retVal = false;
        PrintWriter out = response.getWriter();

        int recipe_id = Integer.parseInt(request.getParameter("recipe_id"));
        retVal = bsci.disableRecipe(recipe_id);

        if (retVal != false) {
            //out.print("Category disabled");
            // RequestDispatcher rd = request.getRequestDispatcher("http://localhost:8080/BakeryProject/ShowList");
            response.sendRedirect("http://localhost:8080/BakeryProject/ShowRecipeList");
        } else {
            out.print("disabling failed");
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
