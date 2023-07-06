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
import za.co.mie.bakeryDao.BakeryDaoOrderImpl;
import za.co.mie.bakeryService.BakeryServiceOrderImpl;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.OrderItem;

@WebServlet(name = "ShowOrderItems", urlPatterns = {"/ShowOrderItems"})
public class ShowOrderItems extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceOrderImpl bsoi = new BakeryServiceOrderImpl(new BakeryDaoOrderImpl(con));
        List<OrderItem> orderItem = null;
        try(PrintWriter out = response.getWriter()){
            
            int order_id =Integer.parseInt( request.getParameter("order_id"));
            orderItem = bsoi.getAllLineItems(order_id);
            if(orderItem != null){
                request.setAttribute("orderItem", orderItem);
                request.getRequestDispatcher("OrderItemList.jsp").forward(request, response);
            }else{
                out.print("failed to show List of order items");
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
