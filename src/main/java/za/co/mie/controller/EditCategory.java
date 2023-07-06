
package za.co.mie.controller;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.mie.bakeryDao.BakeryDaoCategoryImpl;
import za.co.mie.bakeryService.BakeryServiceCategoryImpl;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.Category;


@WebServlet(name = "EditCategory", urlPatterns = {"/EditCategory"})
public class EditCategory extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceCategoryImpl bsci = new BakeryServiceCategoryImpl(new BakeryDaoCategoryImpl(con));
        
        
         int cat_id= Integer.parseInt(request.getParameter("cat_id"));
        
        Category cat = bsci.getSingleCategory(cat_id);
        request.setAttribute("data", cat);
        request.getRequestDispatcher("updateCategoryForm.jsp").forward(request, response);
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    

    

}
