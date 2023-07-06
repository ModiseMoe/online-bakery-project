/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import za.co.mie.bakeryDao.BakeryDaoCategoryImpl;
import za.co.mie.bakeryService.BakeryServiceCategoryImpl;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.Category;

/**
 *
 * @author mdice
 */
@WebServlet(name = "AddCategory", urlPatterns = {"/AddCategory"})
public class AddCategory extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceCategoryImpl bsci = new BakeryServiceCategoryImpl(new BakeryDaoCategoryImpl(con));
        List<Category> catList = null;
        
       
        
        try(PrintWriter out = response.getWriter()){
             String cat_name = request.getParameter("cat_name");
                boolean retVal = bsci.addCategory(new Category(cat_name));
                 catList = bsci.getAllCategories();
                
                if(retVal != false && catList != null){
                    
                    out.print("New Category added");
                    request.setAttribute("categoryList", catList);
                    RequestDispatcher rd = request.getRequestDispatcher("manageCategory.jsp");
                   rd.forward(request, response);
                    
                }else{
                    out.print("failed to add Category");
                    request.setAttribute("categoryList", catList);
                   RequestDispatcher rd = request.getRequestDispatcher("manageCategory.jsp");
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
