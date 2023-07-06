
package za.co.mie.controller;

import java.io.IOException;
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

@WebServlet(name = "EditRecipe", urlPatterns = {"/EditRecipe"})
public class EditRecipe extends HttpServlet{
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceRecipeImpl bsci = new BakeryServiceRecipeImpl(new BakeryDaoRecipeImpl(con));

        int recipe_id = Integer.parseInt(request.getParameter("recipe_id"));

        Recipe recipe = bsci.getSingleRecipe(recipe_id);
        request.setAttribute("data", recipe);
        request.getRequestDispatcher("updateRecipe.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
}
