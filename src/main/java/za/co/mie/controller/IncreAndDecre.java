
package za.co.mie.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.mie.model.CartLineItem;


@WebServlet(name = "IncreAndDecre", urlPatterns = {"/IncreAndDecre"})
public class IncreAndDecre extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        try(PrintWriter out = response.getWriter()){
           String action  = request.getParameter("action");
           int id = Integer.parseInt(request.getParameter("id"));
           
           List<CartLineItem> list = (List<CartLineItem>) request.getSession().getAttribute("cart-List");
           if(action!= null && id>1){
               if(action.equals("inc")){
                   for(CartLineItem l : list){
                       if(l.getProduct().getId() == id){
                           int quantity = l.getQuantity();
                           quantity++;
                           l.setQuantity(quantity);
                           response.sendRedirect("cart.jsp");
                       }
                   }
               }
               if(action.equals("dec")){
                   for(CartLineItem l : list){
                       if((l.getProduct().getId() == id) && (l.getQuantity()>1)){
                           int quantity = l.getQuantity();
                           quantity--;
                           l.setQuantity(quantity);
                           break;
                       }
                   }
                   response.sendRedirect("cart.jsp");
               }
           }else{
                response.sendRedirect("cart.jsp");
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
