package za.co.mie.controller;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.mie.bakeryDao.BakeryDaoIngredientsImpl;
import za.co.mie.bakeryDao.BakeryDaoRecipeImpl;
import za.co.mie.bakeryService.BakeryServiceIngredientsImpl;
import za.co.mie.bakeryService.BakeryServiceRecipeImpl;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.Ingridient;
import za.co.mie.model.Recipe;

@WebServlet(name = "AddRecipe", urlPatterns = {"/AddRecipe"})
public class AddRecipe extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceIngredientsImpl bsci = new BakeryServiceIngredientsImpl(new BakeryDaoIngredientsImpl(con));

        List<Ingridient> ingredientList = bsci.getAllIngridients();
        request.setAttribute("ingredientList", ingredientList);

        RequestDispatcher rd = request.getRequestDispatcher("addRecipeForm.jsp");
        rd.forward(request, response);
    }

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String recipe_name = request.getParameter("recipe_name");
    String description = request.getParameter("description");
    String[] ingredientIds = request.getParameterValues("ingredient_id");
    String[] quantities = request.getParameterValues("quantityInput");

    if (recipe_name.isEmpty() || description.isEmpty() || ingredientIds == null || quantities == null) {
        response.sendRedirect("addRecipeForm.jsp");
        return;
    }

    List<Integer> ingredientIdList = new ArrayList<>();
    List<Integer> quantityList = new ArrayList<>();

    for (int i = 0; i < ingredientIds.length; i++) {
        try {
            int ingredientId = Integer.parseInt(ingredientIds[i]);
            int quantity = Integer.parseInt(quantities[i+1]);
            ingredientIdList.add(ingredientId);
            quantityList.add(quantity);
        } catch (NumberFormatException e) {
            // Display an error message to the user
            response.getWriter().println("Invalid input for ingredient ID or quantity.");
            return;
        }
    }

    List<Ingridient> ingredientList = new ArrayList<>();

    DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
    Connection con = dbm.getConnection();
    BakeryServiceIngredientsImpl bsc = new BakeryServiceIngredientsImpl(new BakeryDaoIngredientsImpl(con));

    for (int i = 0; i < ingredientIdList.size(); i++) {
        int ingredientId = ingredientIdList.get(i);
        int quantity = quantityList.get(i);
        Ingridient ingredient = bsc.getSingleIngridient(ingredientId);
        if (ingredient != null) {
            ingredient.setMinimumStockQuantity(quantity);
            ingredientList.add(ingredient);
        }
    }

    BakeryServiceRecipeImpl bsci = new BakeryServiceRecipeImpl(new BakeryDaoRecipeImpl(con));
    Recipe recipe = new Recipe(recipe_name, description, ingredientList);
    boolean success = bsci.addRecipe(recipe);

    if (success) {
        response.sendRedirect("ShowRecipeList");
    } else {
        response.sendRedirect("addRecipeForm.jsp");
    }
}

}