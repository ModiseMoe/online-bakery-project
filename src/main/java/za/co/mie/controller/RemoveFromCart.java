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

@WebServlet(name = "RemoveFromCart", urlPatterns = {"/RemoveFromCart"})
public class RemoveFromCart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        try (PrintWriter out = response.getWriter()) {
            int id = Integer.parseInt(request.getParameter("id"));
            if (id > 0) {
                List<CartLineItem> list = (List<CartLineItem>) request.getSession().getAttribute("cart-List");
                if (list != null) {
                    for (CartLineItem cli : list) {
                        if (cli.getProduct().getId() == id) {
                            list.remove(list.indexOf(cli));
                            response.sendRedirect("cart.jsp");
                            break;
                        }
                    }

                }
            } else {
                out.print("failed to remove");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
