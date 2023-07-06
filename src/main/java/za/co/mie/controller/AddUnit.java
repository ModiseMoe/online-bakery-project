
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
import za.co.mie.bakeryDao.BakeryDaoIngredientsImpl;
import za.co.mie.bakeryService.BakeryServiceIngredientsImpl;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.Unit;

@WebServlet(name = "AddUnit", urlPatterns = {"/AddUnit"})
public class AddUnit extends HttpServlet {
    
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceIngredientsImpl bsci = new BakeryServiceIngredientsImpl(new BakeryDaoIngredientsImpl(con));
        List<Unit> unitList = null;
        
       
        
        try(PrintWriter out = response.getWriter()){
             String unit_name = request.getParameter("unit_name");
                boolean retVal = bsci.addUnit(new Unit(unit_name));
                 unitList = bsci.getAllUnits();
                
                if(retVal != false && unitList != null){
                    
                    out.print("New Unit added");
                    request.setAttribute("unitList", unitList);
                    RequestDispatcher rd = request.getRequestDispatcher("manageUnits.jsp");
                   rd.forward(request, response);
                    
                }else{
                    out.print("failed to add Unit");
                    request.setAttribute("unitList", unitList);
                   RequestDispatcher rd = request.getRequestDispatcher("manageUnits.jsp");
                    rd.forward(request, response);
                    
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
