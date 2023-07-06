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
import za.co.mie.bakeryDao.BakeryDaoProductImpl;
import za.co.mie.bakeryService.BakeryServiceCategoryImpl;
import za.co.mie.bakeryService.BakeryServiceProductImpl;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.Category;

/**
 *
 * @author mdice
 */
@WebServlet(name = "AddProdToCat", urlPatterns = {"/AddProdToCat"})
public class AddProdToCat extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceProductImpl bspi = new BakeryServiceProductImpl(new BakeryDaoProductImpl(con));
       
        boolean retVal = false;

        try (PrintWriter out = response.getWriter()) {
            int cat_id = Integer.parseInt(request.getParameter("catSelected"));
            int product_id = Integer.parseInt(request.getParameter("product_id"));
            
            
            retVal = bspi.addProductToCategory(product_id, cat_id);

            if (retVal != false ) {
               
                request.getRequestDispatcher("ShowProductList").forward(request, response);
            } else {
                out.print(" request failed");
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
