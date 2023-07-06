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
import za.co.mie.bakeryDao.BakeryDaoCategoryImpl;
import za.co.mie.bakeryDao.BakeryDaoProductImpl;
import za.co.mie.bakeryService.BakeryServiceCategoryImpl;
import za.co.mie.bakeryService.BakeryServiceProductImpl;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.Category;
import za.co.mie.model.Product;

@WebServlet(name = "SearchProduct", urlPatterns = {"/SearchProduct"})
public class SearchProduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();

        BakeryServiceProductImpl bspi = new BakeryServiceProductImpl(new BakeryDaoProductImpl(con));
        BakeryServiceCategoryImpl bsci = new BakeryServiceCategoryImpl(new BakeryDaoCategoryImpl(con));
        List<Product> searchResults = null;
        List<Category> cat3 = null;

        try (PrintWriter out = response.getWriter()) {
            String product_title = request.getParameter("product_title");

            searchResults = bspi.performSearch(product_title);
            cat3 = bsci.getAllCategoriesByStatus();

            if (searchResults != null && cat3 != null) {
                request.setAttribute("search", searchResults);
                request.setAttribute("cat3", cat3);

                if (product_title.isEmpty() && product_title == "") {
                    request.getRequestDispatcher("indexPageList").forward(request, response);
                } else {
                    request.getRequestDispatcher("productBySearch.jsp").forward(request, response);
                }
            } else {
                out.print("search failed");
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
