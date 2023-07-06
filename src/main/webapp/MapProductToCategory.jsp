<%-- 
    Document   : categoryFom
    Created on : May 30, 2023, 7:57:55 PM
    Author     : mdice
--%>
<%@page import="za.co.mie.model.*" %>
<%@page import="java.util.List"%>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    Product data = (Product) request.getAttribute("data");
    Product prod = (Product) request.getAttribute("prod");

%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <%@include file="includes/header.jsp" %>
         <style>
            body {

                background-image: url("images/5.jpg");
                backdrop-filter: blur(5px);
                background-repeat: no-repeat;
                background-size: cover;
                font-family: Arial, sans-serif;

                margin: 0;
                padding: 0;
            }

            .card {
                background-color: rgba(255, 255, 255, 0.6);
                border-radius: 10px;
                max-width: 600px;
                margin: 0 auto;


            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="indexPageList">"To Pie For" Bakery</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="indexPageList"><i class="fas fa-home"></i>Home</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="cart.jsp"><i class="fas fa-shopping-cart"></i>Cart <span class="badge badge-danger"></span></a>
                </li>

                </li>

                <%
           if (auth != null) {%>
                <li class="nav-item">
                    <a class="nav-link" href="ShowOrders"><i class="fas fa-bags-shopping"></i>Orders</a>
                </li> 
                <li class="nav-item">
                    <a class="nav-link" href="EditUser?user_emailAdd=<%=auth.getEmailAddr()%>"><i class="fas fa-user "></i><%=auth.getName()%></a>
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

        <div class="contsiner col-md-5">
            <div class="card  align-center" >
                <div class="card-body" >
                    <caption>Map Product To Category</caption>

                    <form action="AddProdToCat" method="POST">

                        <% List<Category> catList = (List<Category>) request.getAttribute("category-list"); %>
                        <fieldset class="form-group">
                            <div class="container mt-4">
                                <div class="dropdown">
                                    <select name="catSelected" class="select">
                                        <option value="" selected disabled>Select a category</option>
                                        <% for (Category c : catList) {%>
                                        <option value="<%= c.getId()%>"><%= c.getCategoryName()%></option>
                                        <% }%>
                                    </select>

                                </div>
                            </div>
                        </fieldset>

                        <input type="hidden" name="product_id" value="<%= prod.getId()%>" class="form-control">
                        <button type="submit" class="btn btn-dark">Save</button>
                        <a href="ShowProductList" class="btn btn-dark">Cancel</a>
                    </form>


                </div>

            </div>
        </div>


        <style>
            .navbar-nav .nav-item {
                margin-right: 10px;
            }

            .nav-link text-white{
                margin-bottom: 100px
            }

            .form-group {
                margin-bottom: 0;
            }

            .dropdown {
                display: inline-block;
            }

            .dropdown select {
                display: block;
                width: 100%;
                padding: 0.375rem 0.75rem;
                font-size: 1rem;
                font-weight: 400;
                line-height: 1.5;
                color: #212529;
                background-color: #fff;
                background-clip: padding-box;
                border: 1px solid #ced4da;
                border-radius: 0.25rem;
                transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
                margin-bottom: 20px;
            }

            .select {
                width: 200px;
            }
        </style>


        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>
