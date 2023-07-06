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
import za.co.mie.model.Recipe;

@WebServlet(name = "UpdateRecipe", urlPatterns = {"/UpdateRecipe"})
public class UpdateRecipe extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceRecipeImpl bsci = new BakeryServiceRecipeImpl(new BakeryDaoRecipeImpl(con));
        boolean retValue = false;

        try (PrintWriter out = response.getWriter()) {
            String recipe_name = request.getParameter("recipe_name");
            String description = request.getParameter("description");
            int recipe_id = Integer.parseInt(request.getParameter("recipe_id"));
            retValue = bsci.updateRecipe(new Recipe(recipe_name, description), recipe_id);

            if (retValue != false) {
                response.sendRedirect("http://localhost:8080/BakeryProject/ShowRecipeList");

            } else {
                out.print("failed to update Recipe");
                // RequestDispatcher rd = request.getRequestDispatcher("updateCategoryForm.jsp");
                // rd.forward(request, response);
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
