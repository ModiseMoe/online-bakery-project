<%-- 
    Document   : adminPage
    Created on : May 29, 2023, 5:09:26 PM
    Author     : mdice
--%>


<%@page import="za.co.mie.bakeryService.BakeryServiceProductImpl"%>
<%@page import="za.co.mie.bakeryDao.BakeryDaoProductImpl"%>
<%@page import="java.sql.Connection"%>
<%@page import="za.co.mie.db.listener.DBManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="za.co.mie.model.User" %>
<%@page import="za.co.mie.model.*" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page import="java.text.DecimalFormat" %>

<%

    User auth = (User) request.getSession().getAttribute("auth");

    DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
    Connection con = dbm.getConnection();
    BakeryServiceProductImpl bspi = new BakeryServiceProductImpl(new BakeryDaoProductImpl(con));


%>   
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <%@include file="includes/header.jsp" %>
         <style>
            body {

                background-image: url("images/4.webp");
                backdrop-filter: blur(5px);
                background-repeat: no-repeat;
                background-size: cover;
                font-family: Arial, sans-serif;

                margin: 0;
                padding: 0;
            }

            .container {
                background-color: rgba(255, 255, 255, 0.6);
                border-radius: 10px;
                max-width: 1000px;
                margin: 0 auto;
            }
            .table table-bordered{
                background-color: rgba(255, 255, 255, 0.6);
                border-radius: 10px;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="AdminLandingPage.jsp">"To Pie For" Bakery</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">

                    <% if (auth != null && auth.getUserRole().equals("Admin")) {%>
                    <li class="nav-item">

                        <a class="nav-link" href="#"><i class="fas fa-user"></i><%= auth.getName()%></a>
                    </li> 

                    <li class="nav-item">
                        <a class="nav-link" href="Logout">Logout</a>
                    </li>        

                    <%} else {%>
                    <li class="nav-item">
                        <a class="nav-link" href="login.jsp">Login</a>
                    </li>
                    <%}
                    %>
                </ul>
            </div>
        </nav>
        <div class="row">
            <div class="container">
                <h3 class="text-center">Welcome to the Product Panel</h3>
                <hr>

                <div class="container justify-content-left">
                    <div class="row justify-content-left">
                        <div class="col-lg-4 col-md-6 mt-3 ">
                            <a href="RecipeListAtrribute" class="btn btn-dark">Add New Product</a>
                        </div>
                    </div>

                    <div class="container justify-content-right ">
                        <div class="row justify-content-right">
                            <div class="col-lg-4 col-md-6 mt-3 ">
                                <a href="AdminLandingPage.jsp" class="btn btn-dark">Exit</a>
                            </div>
                        </div>
                    </div>

                    <form action="showProductList" method="GET">

                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Product title</th>
                                    <th>Product description</th>
                                    <th>product Warnings</th>
                                    <th>Price</th>
                                    <th>Category</th>

                                    <th>Status</th>
                                    <th>Edit</th>
                                    <th>action</th>
                                    <th>map to category</th>


                                </tr>
                            </thead>

                            <%
                                DecimalFormat decimalFormat = new DecimalFormat("0.00");

                                List<Product> ProdList = (List<Product>) request.getAttribute("productList");
                                for (Product myList : ProdList) {%>


                            <tr>
                                <td><%= myList.getProduct_title()%></td>
                                <td><%= myList.getDescription()%></td>
                                <td><%= myList.getProductWarnigs()%></td>
                                <td> <%=decimalFormat.format(myList.getProductPrice())%></td>  
                                <td><%
                                    if (bspi.getCategoryForProduct(myList.getId()) == null) {
                                        out.print("not mapped");
                                    } else {
                                        out.print(bspi.getCategoryForProduct(myList.getId()));
                                    }

                                    %> </td>
                                <td><%                                if (myList.isStatus()) {
                                        out.print("Active");
                                    } else {
                                        out.print("Disabled");
                                    }
                                    %></td>
                                <td> <a href="EditProduct?product_id=<%=myList.getId()%>" class="btn btn-dark"><i class="fas fa-pen"></i></a></td>
                                <td>
                                    <% if (myList.isStatus()) {%>
                                    <a href="DisableProductStatus?product_id=<%=myList.getId()%>" class="btn btn-danger">Disable</a>
                                    <% } else {%>
                                    <a href="ActivateProductStatus?product_id=<%=myList.getId()%>" class="btn btn-success">Activate</a>
                                    <% }%>
                                </td>
                                <td> <a href="ProdMapID?product_id=<%=myList.getId()%>" class="btn btn-dark">Map To Category</a></td>
                            </tr>

                            <%}%>


                        </table>
                    </form>
                </div>
            </div>

        </div>

        <%@include file="includes/footer.jsp" %>

    </body>
</html>
