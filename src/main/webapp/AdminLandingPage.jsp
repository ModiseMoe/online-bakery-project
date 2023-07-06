<%-- 
    Document   : adminPage
    Created on : May 29, 2023, 5:09:26 PM
    Author     : mdice
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="za.co.mie.model.*" %>
<%@page import="java.util.List"%>

<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {

//        response.sendRedirect("index.jsp");
    }
%>   
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <%@include file="includes/header.jsp" %>

        <style>
            body{

                background-image: url("images/4.webp");
                backdrop-filter: blur(8px);
                background-repeat: no-repeat;
                background-size: cover;
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
            }

            .container{
                max-width: 1200px;
                margin-top: 30px

            }
            .radius-10 {
                border-radius: 10px !important;
            }

            .border-info {
                border-left: 5px solid  #0dcaf0 !important;
            }
            .border-danger {
                border-left: 5px solid  #fd3550 !important;
            }
            .border-success {
                border-left: 5px solid  #15ca20 !important;
            }
            .border-warning {
                border-left: 5px solid  #ffc107 !important;
            }


            .card {
                position: relative;
                display: flex;
                flex-direction: column;
                min-width: 0;
                word-wrap: break-word;
                background-color: #fff;
                background-clip: border-box;
                border: 0px solid rgba(0, 0, 0, 0);
                border-radius: .25rem;
                margin-bottom: 1.5rem;
                box-shadow: 0 2px 6px 0 rgb(218 218 253 / 65%), 0 2px 6px 0 rgb(206 206 238 / 54%);
            }
            .bg-gradient-scooter {
                background: #17ead9;
                background: -webkit-linear-gradient(
                    45deg
                    , #17ead9, #6078ea)!important;
                background: linear-gradient(
                    45deg
                    , #17ead9, #6078ea)!important;
            }
            .widgets-icons-2 {
                width: 56px;
                height: 56px;
                display: flex;
                align-items: center;
                justify-content: center;
                background-color: #ededed;
                font-size: 27px;
                border-radius: 10px;
            }
            .rounded-circle {
                border-radius: 50%!important;
            }
            .text-white {
                color: #fff!important;
            }
            .ms-auto {
                margin-left: auto!important;
            }
            .bg-gradient-bloody {
                background: #f54ea2;
                background: -webkit-linear-gradient(
                    45deg
                    , #f54ea2, #ff7676)!important;
                background: linear-gradient(
                    45deg
                    , #f54ea2, #ff7676)!important;
            }

            .bg-gradient-ohhappiness {
                background: #00b09b;
                background: -webkit-linear-gradient(
                    45deg
                    , #00b09b, #96c93d)!important;
                background: linear-gradient(
                    45deg
                    , #00b09b, #96c93d)!important;
            }

            .bg-gradient-blooker {
                background: #ffdf40;
                background: -webkit-linear-gradient(
                    45deg
                    , #ffdf40, #ff8359)!important;
                background: linear-gradient(
                    45deg
                    , #ffdf40, #ff8359)!important;
            }
        </style>

    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="#">"To Pie For" Bakery</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">

                    <%
                        if (auth != null && auth.getUserRole().equals("Admin")) {%>
                    <li class="nav-item">
                        <a class="nav-link" href="EditUser?user_emailAdd=<%=auth.getEmailAddr()%>"><i class="fas fa-user"></i><%= auth.getName()%></a>
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
                <h3 class="text-center" style="color: grey;">Welcome To The Admin Dashboard</h3>
                <hr><!-- comment -->
            </div>
            <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
            <div class="container">
                <div class="row row-cols-1 row-cols-md-2 row-cols-xl-3">
                    <a href="http://localhost:8080/BakeryProject/ShowList">
                        <div class="col">
                            <div class="card radius-10 border-start border-0 border-3 border-info">
                                <div class="card-body">
                                    <div class="d-flex align-items-center">
                                        <div>
                                            <p class="mb-0 text-secondary">Manage Categories</p>
                                            <p class="mb-0 font-13">Add/Edit/disable/activate</p>

                                        </div>
                                        <div class="widgets-icons-2 rounded-circle bg-gradient-scooter text-white ms-auto"><i class="fa fa-shopping-bag"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>

                    <a href="http://localhost:8080/BakeryProject/ShowProductList">
                        <div class="col">
                            <div class="card radius-10 border-start border-0 border-3 border-success">
                                <div class="card-body">
                                    <div class="d-flex align-items-center">
                                        <div>
                                            <p class="mb-0 text-secondary">Manage Products</p>
                                            <p class="mb-0 font-13">Add/Edit/disable/activate</p>

                                        </div>
                                        <div class="widgets-icons-2 rounded-circle bg-gradient-ohhappiness text-white ms-auto"><i class="fa fa-shopping-cart"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div> 
                    </a>

                    <a href="http://localhost:8080/BakeryProject/ShowRecipeList">
                        <div class="col">
                            <div class="card radius-10 border-start border-0 border-3 border-warning">
                                <div class="card-body">
                                    <div class="d-flex align-items-center">
                                        <div>
                                            <p class="mb-0 text-secondary">Manage Recipes</p>
                                            <p class="mb-0 font-13"> Add/Edit/disable/activate</p>

                                        </div>
                                        <div class="widgets-icons-2 rounded-circle bg-gradient-blooker text-white ms-auto"><i class="fa fa-flask"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>

                    <a href="http://localhost:8080/BakeryProject/ShowIngredientList" >
                        <div class="col">
                            <div class="card radius-10 border-start border-0 border-3 border-info">
                                <div class="card-body">
                                    <div class="d-flex align-items-center">
                                        <div>
                                            <p class="mb-0 text-secondary">Manage Ingredients</p>
                                            <p class="mb-0 font-13">Add/Edit</p>

                                        </div>
                                        <div class="widgets-icons-2 rounded-circle bg-gradient-scooter text-white ms-auto"><i class="fa fa-barcode"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>

                    <a href="http://localhost:8080/BakeryProject/ShowUnitList">
                        <div class="col">
                            <div class="card radius-10 border-start border-0 border-3 border-danger">
                                <div class="card-body">
                                    <div class="d-flex align-items-center">
                                        <div>
                                            <p class="mb-0 text-secondary">Manage Units</p>
                                            <p class="mb-0 font-13">Add/Edit</p>

                                        </div>
                                        <div class="widgets-icons-2 rounded-circle bg-gradient-bloody text-white ms-auto"><i class="fa fa-thermometer-full"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>

                    <a href="http://localhost:8080/BakeryProject/ViewUsers" >
                        <div class="col">
                            <div class="card radius-10 border-start border-0 border-3 border-warning">
                                <div class="card-body">
                                    <div class="d-flex align-items-center">
                                        <div>
                                            <p class="mb-0 text-secondary">Manage Users</p>
                                            <p class="mb-0 font-13">Add/disable/activate</p>

                                        </div>
                                        <div class="widgets-icons-2 rounded-circle bg-gradient-blooker text-white ms-auto"><i class="fa fa-users"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>

                    <a href="http://localhost:8080/BakeryProject/reportDashboard.jsp">
                        <div class="col">
                            <div class="card radius-10 border-start border-0 border-3 border-success">
                                <div class="card-body">
                                    <div class="d-flex align-items-center">
                                        <div>
                                            <p class="mb-0 text-secondary">Reports</p>
                                            <p class="mb-0 font-13">View/Print</p>

                                        </div>
                                        <div class="widgets-icons-2 rounded-circle bg-gradient-ohhappiness text-white ms-auto"><i class="fa fa-bar-chart"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>

                </div>
            </div>

            <%@include file="includes/footer.jsp" %>

    </body>
</html>

