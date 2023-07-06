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

%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <%@include file="includes/header.jsp" %>
        <style>
            body {

                background-image: url("background2.jpg");
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
            <a class="navbar-brand" href="#">"To Pie For" Bakery</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">

                    <%           if (auth != null && auth.getUserRole().equals("Admin")) {%>
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
                    <form action="UpdateUser" method="POST">
                        <caption>Update User Profile</caption>
                        <%
                            User userData = (User) request.getAttribute("userData");
                        %>
                        <fieldset class="form-group">
                            <label>Name</label>
                            <input type="text" name="user_name" value="<%=userData.getName()%>" class="form-control" required>
                            <input type="hidden" name="userId" value="<%=userData.getEmailAddr()%>" class="form-control">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Surname</label>
                            <input type="text" name="user_surname" value="<%=userData.getSurname()%>" class="form-control" required>
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Email address</label>
                            <input type="text" name="user_emailAdd" value="<%=userData.getEmailAddr()%>" class="form-control" required>
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Mobile Number</label>
                            <input type="text" name="user_mobileNum" value="<%=userData.getMobileNumber()%>" class="form-control">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Password</label>
                            <input type="password" name="user_pwd" value="" class="form-control" required>
                        </fieldset>
                        <fieldset class="form-group">
                            <label>ID no</label>
                            <input type="text" name="user_idNo" value="" class="form-control">
                        </fieldset>


                        <button type="submit" class="btn btn-dark" >Save</button>
                        <%
                    if (auth != null && auth.getUserRole().equals("Admin")) {%>
                        <a href="AdminLandingPage.jsp" class="btn btn-dark">Cancel</a>
                        <%} else {%>
                        <a href="indexPageList" class="btn btn-dark">Cancel</a>
                        <%}%>
                    </form>

                </div>

            </div>
        </div>




    </body>
</html>
