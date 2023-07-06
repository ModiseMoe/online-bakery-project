
package za.co.mie.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.mie.bakeryDao.BakeryDaoRecipeImpl;
import za.co.mie.bakeryService.BakeryServiceRecipeImpl;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.Recipe;


@WebServlet(name = "RecipeListAtrribute", urlPatterns = {"/RecipeListAtrribute"})
public class RecipeListAtrribute extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceRecipeImpl bsci = new BakeryServiceRecipeImpl(new BakeryDaoRecipeImpl(con));
        List<Recipe> recipe = null;
        try (PrintWriter out = response.getWriter()) {
            
           recipe = bsci.getAllRecipes();
           if(recipe != null){
               request.setAttribute("recipe", recipe);
               request.getRequestDispatcher("addProductForm.jsp").forward(request, response);
           }else{
               out.print("List not found");
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
