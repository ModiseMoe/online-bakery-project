
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
import za.co.mie.model.Order;
import za.co.mie.model.User;


@WebServlet(name = "ShowOrders", urlPatterns = {"/ShowOrders"})
public class ShowOrders extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceOrderImpl bsoi = new BakeryServiceOrderImpl(new BakeryDaoOrderImpl(con));
         User auth = (User) request.getSession().getAttribute("auth");
        List<Order> orders = null;
        try(PrintWriter out = response.getWriter()){
            orders = bsoi.getAllOrdersForUser(auth.getEmailAddr());
            
            if(orders != null){
                request.setAttribute("orders", orders);
               
                request.getRequestDispatcher("orders.jsp").forward(request, response);
            }else{
                out.print("failed to show orders");
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
