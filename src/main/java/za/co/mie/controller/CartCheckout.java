package za.co.mie.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.mie.bakeryDao.BakeryDaoOrderImpl;
import za.co.mie.bakeryService.BakeryServiceOrderImpl;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.CartLineItem;
import za.co.mie.model.Email;
import za.co.mie.model.Order;
import za.co.mie.model.OrderItem;
import za.co.mie.model.User;

@WebServlet(name = "CartCheckout", urlPatterns = {"/CartCheckout"})
public class CartCheckout extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceOrderImpl bsoi = new BakeryServiceOrderImpl(new BakeryDaoOrderImpl(con));
        Email mail = new Email();
        boolean retVal = false;

        try (PrintWriter out = response.getWriter()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            List<CartLineItem> shoppingCart = (ArrayList<CartLineItem>) request.getSession().getAttribute("cart-List");
            User auth = (User) request.getSession().getAttribute("auth");

            if (shoppingCart != null && !shoppingCart.isEmpty() && auth != null) {
                Order order = new Order(); // Create a single Order object
                order.setUserId(auth.getEmailAddr());
                try {
                    Date orderDate = formatter.parse(formatter.format(date));
                    order.setOrderDate(orderDate);
                } catch (ParseException e) {
                    System.out.println("Error parsing order date: " + e.getMessage());
                }

                List<OrderItem> orderItems = new ArrayList<>(); // Create a list to store OrderItems

                for (CartLineItem cli : shoppingCart) {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct_id(cli.getProduct().getId());
                    orderItem.setProductQuantity(cli.getQuantity());
                    orderItem.setProduct_title(cli.getProduct().getProduct_title());
                    orderItem.setUnitPrice(cli.getProduct().getProductPrice());
                    orderItems.add(orderItem);
                }

                order.setOrderItems(orderItems); // Set the list of OrderItems to the Order object

                retVal = bsoi.placeOrder(order); // Pass the single Order object to the placeOrder method
             
                
                if (retVal && orderItems != null) {
                    shoppingCart.clear();
                    request.getRequestDispatcher("ShowOrders").forward(request, response);
                } else {
                    out.print("Order placement failed");
                }
            } else {
                if (auth == null) {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("cart.jsp").forward(request, response);
                }
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
