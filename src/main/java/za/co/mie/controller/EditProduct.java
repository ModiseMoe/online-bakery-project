
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


@WebServlet(name = "EditProduct", urlPatterns = {"/EditProduct"})
public class EditProduct extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceProductImpl bspi = new BakeryServiceProductImpl(new BakeryDaoProductImpl(con));
         PrintWriter out = response.getWriter();
         
        int product_id =Integer.parseInt(request.getParameter("product_id")) ;
       Product products =  bspi.getSingleProduct(product_id);
       request.setAttribute("data", products);
        
        
        if(products != null){
           request.getRequestDispatcher("updateProductForm.jsp").forward(request, response);
           
        }else{
            out.print("no products");
        }
        
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    

}
