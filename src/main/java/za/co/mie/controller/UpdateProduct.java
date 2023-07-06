
package za.co.mie.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.mie.bakeryDao.BakeryDaoProductImpl;
import za.co.mie.bakeryService.BakeryServiceProductImpl;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.Product;


@WebServlet(name = "UpdateProduct", urlPatterns = {"/UpdateProduct"})
public class UpdateProduct extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceProductImpl bspi = new BakeryServiceProductImpl(new BakeryDaoProductImpl(con));
        boolean retVal = false;
        
        try(PrintWriter out = response.getWriter()){
            String product_title = request.getParameter("product_title");
            int product_id = Integer.parseInt(request.getParameter("product_id"));
            String  description = request.getParameter("product_desc" );
            String productWarnigs = request.getParameter("productWarnigs");
         double  productPrice = Double.parseDouble(request.getParameter("productPrice"));
         String image = request.getParameter("image");
         retVal = bspi.updateProduct(product_id, new Product(product_title,description, productWarnigs, productPrice ,image ));
         if(retVal != false){
             response.sendRedirect("http://localhost:8080/BakeryProject/ShowProductList");
         }else{
             response.sendRedirect("http://localhost:8080/BakeryProject/ShowProductList");
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
