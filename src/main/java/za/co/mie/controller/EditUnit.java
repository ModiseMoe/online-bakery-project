
package za.co.mie.controller;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.mie.bakeryDao.BakeryDaoIngredientsImpl;
import za.co.mie.bakeryService.BakeryServiceIngredientsImpl;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.Unit;

@WebServlet(name = "EditUnit", urlPatterns = {"/EditUnit"})
public class EditUnit extends HttpServlet {
    
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceIngredientsImpl bsci = new BakeryServiceIngredientsImpl(new BakeryDaoIngredientsImpl(con));
        
        
         int unit_id= Integer.parseInt(request.getParameter("unit_id"));
        
        Unit unit = bsci.getSingleUnit(unit_id);
        request.setAttribute("data", unit);
        request.getRequestDispatcher("updateUnitForm.jsp").forward(request, response);
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
