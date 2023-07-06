/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import za.co.mie.bakeryDao.BakeryDaoProductImpl;
import za.co.mie.bakeryService.BakeryServiceProductImpl;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.Product;


@WebServlet(name = "AddProduct", urlPatterns = {"/AddProduct"})
public class AddProduct extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceProductImpl bspi = new BakeryServiceProductImpl(new BakeryDaoProductImpl(con));
        List<Product> product = null;
        boolean retVal = false;
                
        try(PrintWriter out = response.getWriter()){
           int recipe_id = Integer.parseInt(request.getParameter("catSelected"));
            String product_title = request.getParameter("product_title");
            String  description = request.getParameter("product_desc" );
            String productWarnigs = request.getParameter("productWarnigs");
         double  productPrice = Double.parseDouble(request.getParameter("productPrice"));
         String image = request.getParameter("image");
            
            retVal = bspi.addNewProduct(new Product( product_title,  description, productWarnigs, productPrice , image) ,recipe_id );
            
            if( retVal != false){
                response.sendRedirect("http://localhost:8080/BakeryProject/ShowProductList");
            }else{
                out.print("not succesfull");
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
