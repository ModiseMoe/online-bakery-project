
package za.co.mie.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.mie.bakeryDao.BakeryDaoIngredientsImpl;
import za.co.mie.bakeryService.BakeryServiceIngredientsImpl;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.Ingridient;

@WebServlet(name = "UpdateIngredient", urlPatterns = {"/UpdateIngredient"})
public class UpdateIngredient extends HttpServlet{
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceIngredientsImpl bsci = new BakeryServiceIngredientsImpl(new BakeryDaoIngredientsImpl(con));
        boolean retValue = false;
        
        try(PrintWriter out = response.getWriter()){
            String ingredient_name = request.getParameter("ingredient_name");
            int quantityOnHand= Integer.parseInt(request.getParameter("quantityOnHand"));
            int minimumStockQty= Integer.parseInt(request.getParameter("minimumStockQty"));
            int ingredient_id= Integer.parseInt(request.getParameter("ingredient_id"));
           retValue =  bsci.updateIngredient(new Ingridient(ingredient_name,quantityOnHand,minimumStockQty), ingredient_id);
           
            if(retValue != false){
               response.sendRedirect("http://localhost:8080/BakeryProject/ShowIngredientList");
              
           }else{
                 out.print("failed to update Ingredient");
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
