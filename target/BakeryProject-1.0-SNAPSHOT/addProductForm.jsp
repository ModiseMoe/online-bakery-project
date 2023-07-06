<%-- 
    Document   : categoryFom
    Created on : May 30, 2023, 7:57:55 PM
    Author     : mdice
--%>
<%@page import="za.co.mie.model.*" %>
<%@page import="java.util.List"%>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {

//        response.sendRedirect("index.jsp");
    }
%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <a class="navbar-brand" href="AdminLandingPage.jsp">"To Pie For" Bakery</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">

                    <%
           if (auth != null && auth.getUserRole().equals("Admin")) {%>
                    <li class="nav-item">

                        <a class="nav-link" href="#"><i class="fas fa-user"></i>user</a>
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
                    <form action="AddProduct" method="POST">
                        <caption>Add New Product</caption>

                        <fieldset class="form-group">
                            <label>Product title</label>
                            <input type="text" name="product_title" class="form-control">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Product Description</label>
                            <input type="text" name="product_desc" class="form-control">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Product Warnings</label>
                            <input type="text" name="productWarnigs" class="form-control">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Price</label>
                            <input type="text" name="productPrice" class="form-control">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Image</label>
                            <input type="text" name="image" class="form-control">
                        </fieldset>
                        <% List<Recipe> recipe = (List<Recipe>) request.getAttribute("recipe"); %>

                        <fieldset class="form-group">
                             <label for="Unit">Recipe:</label>
                            <select name="catSelected" class="select">
                                <option value="" selected disabled>Select a Recipe</option>
                                <% for (Recipe r : recipe) {%>
                                <option value="<%=r.getRecipeId()%>"><%=r.getRecipeName()%></option>
                                <% }%>
                            </select>
                        </fieldset>

                        <button type="submit" class="btn btn-dark" >Save</button>
                        <a href="ShowProductList" class="btn btn-dark">Cancel</a>
                    </form>

                </div>

            </div>
        </div>




    </body>
</html>
