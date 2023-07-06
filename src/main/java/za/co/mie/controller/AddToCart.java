package za.co.mie.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import za.co.mie.model.CartLineItem;

@WebServlet(name = "AddToCart", urlPatterns = {"/AddToCart"})
public class AddToCart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        int product_id = Integer.parseInt(request.getParameter("product_id"));

        if (product_id > 0) {
            HttpSession session = request.getSession();    // beware of a null session
            List<CartLineItem> cart = (List<CartLineItem>) session.getAttribute("cart-List");

            if (cart == null) {
                cart = new ArrayList<>();
                cart.add(new CartLineItem(product_id, 1));
                session.setAttribute("cart-List", cart);
                request.getRequestDispatcher("indexPageList").forward(request, response);
            } else {
                boolean addIt = true;
                for (CartLineItem c : cart) {
                    if (c.getProduct().getId() == product_id) {
                        addIt = false;
                        out.println("<h3 style='color:crimson; text-align:center'>Oops!! item already added to cart.<a href='cart.jsp'>Go to Cart</a></h3>");
                        break;
                    }
                }

                if (addIt) {
                    cart.add(new CartLineItem(product_id, 1));
                }

                request.getRequestDispatcher("indexPageList").forward(request, response);
            }
        }
    }

    // *************************************************
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
