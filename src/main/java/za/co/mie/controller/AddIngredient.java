package za.co.mie.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.mie.bakeryDao.BakeryDaoIngredientsImpl;
import za.co.mie.bakeryService.BakeryServiceIngredientsImpl;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.Ingridient;
import za.co.mie.model.Unit;

@WebServlet(name = "AddIngredient", urlPatterns = {"/AddIngredient"})
public class AddIngredient extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceIngredientsImpl bsci = new BakeryServiceIngredientsImpl(new BakeryDaoIngredientsImpl(con));

        List<Unit> unitList = bsci.getAllUnits();
        request.setAttribute("unitList", unitList);
        RequestDispatcher rd = request.getRequestDispatcher("addIngredientForm.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceIngredientsImpl bsci = new BakeryServiceIngredientsImpl(new BakeryDaoIngredientsImpl(con));
        List<Ingridient> ingredientList = null;

        String ingredient_name = request.getParameter("ingredient_name");
        int quantityOnHand = Integer.parseInt(request.getParameter("quantityOnHand"));
        int minimumStockQty = Integer.parseInt(request.getParameter("minimumStockQty"));
        int unit_id = Integer.parseInt(request.getParameter("unit_id"));
        boolean retVal = bsci.addIngredient(new Ingridient(ingredient_name, quantityOnHand, minimumStockQty), unit_id);
        ingredientList = bsci.getAllIngridients();

        request.setAttribute("ingredientList", ingredientList);
        RequestDispatcher rd = request.getRequestDispatcher("manageIngredients.jsp");
        rd.forward(request, response);
    }
}
