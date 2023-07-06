
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
import za.co.mie.bakeryDao.BakeryDaoCategoryImpl;
import za.co.mie.bakeryDao.BakeryDaoProductImpl;
import za.co.mie.bakeryService.BakeryServiceCategoryImpl;
import za.co.mie.bakeryService.BakeryServiceProductImpl;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.Category;
import za.co.mie.model.Product;


@WebServlet(name = "FllterByCat", urlPatterns = {"/FllterByCat"})
public class FllterByCat extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
       BakeryServiceProductImpl bspi = new BakeryServiceProductImpl(new BakeryDaoProductImpl(con));
       BakeryServiceCategoryImpl bsci = new BakeryServiceCategoryImpl(new BakeryDaoCategoryImpl(con));
       
       List<Product> prodByCat = null;
        List<Category> cat2 = null;
        try (PrintWriter out = response.getWriter()) {
            int cat_id = Integer.parseInt(request.getParameter("catSelected"));
            prodByCat = bspi.getProductsbyCategory(cat_id);
             cat2 = bsci.getAllCategoriesByStatus();
            

            if (prodByCat != null && cat2 != null ) {
                request.setAttribute("prod-cat", prodByCat);
                request.setAttribute("cat2", cat2);
                request.getRequestDispatcher("ProdByCat.jsp").forward(request, response);
            } else {
                
                out.print("product list not Found");
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
