/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import za.co.mie.bakeryService.BakeryServiceCategoryImpl;
import za.co.mie.db.listener.DBManager;

/**
 *
 * @author mdice
 */
@WebServlet(name = "ActivateCategoryStatus", urlPatterns = {"/ActivateCategoryStatus"})
public class ActivateCategoryStatus extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceCategoryImpl bsci = new BakeryServiceCategoryImpl(new BakeryDaoCategoryImpl(con));
        boolean retVal = false;
        PrintWriter out = response.getWriter();
        
       int cat_id= Integer.parseInt(request.getParameter("cat_id"));
       retVal =bsci.activateCategory(cat_id);
       
        if(retVal != false){
            out.print("Category activated");
            response.sendRedirect("http://localhost:8080/BakeryProject/ShowList");
            
        }else{
            out.print("activation failed");
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
