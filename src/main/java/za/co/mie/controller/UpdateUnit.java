
package za.co.mie.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.mie.bakeryDao.BakeryDaoCategoryImpl;
import za.co.mie.bakeryDao.BakeryDaoIngredientsImpl;
import za.co.mie.bakeryService.BakeryServiceCategoryImpl;
import za.co.mie.bakeryService.BakeryServiceIngredientsImpl;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.Unit;

@WebServlet(name = "UpdateUnit", urlPatterns = {"/UpdateUnit"})
public class UpdateUnit extends HttpServlet{
    
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceIngredientsImpl bsci = new BakeryServiceIngredientsImpl(new BakeryDaoIngredientsImpl(con));
        boolean retValue = false;
        
        try(PrintWriter out = response.getWriter()){
            String unit_name = request.getParameter("unit_name");
            int unit_id= Integer.parseInt(request.getParameter("unit_id"));
           retValue =  bsci.updateUnit(new Unit(unit_name), unit_id);
           
            if(retValue != false){
                out.print("Unit updated");
               response.sendRedirect("http://localhost:8080/BakeryProject/ShowUnitList");
              
           }else{
                 out.print("failed to update unit");
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
