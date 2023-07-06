
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
import za.co.mie.bakeryDao.BakeryDaoAddressImpl;
import za.co.mie.bakeryDao.BakeryDaoOrderImpl;
import za.co.mie.bakeryService.BakeryServiceAddressImpl;
import za.co.mie.bakeryService.BakeryServiceOrderImpl;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.Address;
import za.co.mie.model.Order;
import za.co.mie.model.User;


@WebServlet(name = "GetSingleOrder", urlPatterns = {"/GetSingleOrder"})
public class GetSingleOrder extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceOrderImpl bsoi = new BakeryServiceOrderImpl(new BakeryDaoOrderImpl(con));
         BakeryServiceAddressImpl bsai = new BakeryServiceAddressImpl(new BakeryDaoAddressImpl(con));
          User auth = (User) request.getSession().getAttribute("auth");
        List<Address> addressList = null;
       
        try (PrintWriter out = response.getWriter()) {
           int order_id =Integer.parseInt(request.getParameter("order_id")) ;
           Order order = bsoi.getSingleOrder(order_id);
           addressList = bsai.getAddressList(auth.getEmailAddr());
           
           
           if(order != null && addressList != null){
               request.setAttribute("addressList", addressList);
               request.setAttribute("order-for-id", order);
               request.getRequestDispatcher("address.jsp").forward(request, response);
           }else{
               out.print("failed");
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
