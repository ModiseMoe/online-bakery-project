<%-- 
    Document   : manageIngredients
    Created on : Jun 7, 2023, 3:09:27 PM
    Author     : Train
--%>

<%@page import="za.co.mie.bakeryService.BakeryServiceIngredientsImpl"%>
<%@page import="za.co.mie.bakeryDao.BakeryDaoIngredientsImpl"%>
<%@page import="java.sql.Connection"%>
<%@page import="za.co.mie.db.listener.DBManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="za.co.mie.model.User" %>
<%@page import="za.co.mie.model.Ingridient" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>

<%
    User auth = (User) request.getSession().getAttribute("auth");
    DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
    Connection con = dbm.getConnection();
    BakeryServiceIngredientsImpl bsci = new BakeryServiceIngredientsImpl(new BakeryDaoIngredientsImpl(con));

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
                background-color: rgba(60, 40, 50, 0.6);
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

                    <%if (auth != null && auth.getUserRole().equals("Admin")) {%>
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
                    <%}%>
                </ul>
            </div>
        </nav>
        <div class="row">
            <div class="container">
                <h3 class="text-center">Welcome to the Ingredients Panel</h3>
                <hr><!-- comment -->

                <div class="container justify-content-left">
                    <div class="row justify-content-left">
                        <div class="col-lg-4 col-md-6 mt-3 ">
                            <a href="AddIngredient?action=get" class="btn btn-dark">Add New Ingredient</a>
                        </div>
                    </div>

                    <div class="container justify-content-right ">
                        <div class="row justify-content-right">
                            <div class="col-lg-4 col-md-6 mt-3 ">
                                <a href="AdminLandingPage.jsp" class="btn btn-dark">Exit</a>
                            </div>
                        </div>
                    </div>

                    <form action="showIngredientList" method="GET">

                        <style>
                            table.table-bordered {
                                border-color: black;
                            }

                            table.table-bordered th,
                            table.table-bordered td {
                                border-color: black;
                            }
                        </style>

                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Ingredient name</th>
                                    <th>Quantity On Hand</th>
                                    <th>Minimum Stock Qty</th>
                                    <th>Unit</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <% List<Ingridient> myCatList = (List<Ingridient>) request.getAttribute("ingredientList");
                                for (Ingridient list : myCatList) {%>
                            <tr>
                                <td><%= list.getIngridientsName()%></td>
                                <td><%= list.getQuantityOnHand()%></td>
                                <td><%= list.getMinimumStockQuantity()%></td>
                                <td><%=bsci.getUnitForIngredient(list.getIngredientId())%></td>
                                <td>
                                    <a href="EditIngredient?ingredient_id=<%= list.getIngredientId()%>" class="btn btn-dark">
                                        <i class="fas fa-pen"></i>
                                    </a>
                                </td>
                            </tr>
                            <% }%>
                        </table>

                    </form>
                </div>

            </div>
        </div>

        <%@include file="includes/footer.jsp" %>

    </body>
</html>


