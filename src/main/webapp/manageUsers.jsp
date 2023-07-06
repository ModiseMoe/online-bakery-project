<%-- 
    Document   : manageUsers
    Created on : Jun 15, 2023, 9:14:50 AM
    Author     : Train
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="za.co.mie.model.User" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>

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
                background-color: rgba(255, 255,255, 0.6);
                border-radius: 10px;
                max-width: 1000px;
                margin: 0 auto;
            }
            .table table-bordered{
               
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

                    <%
                        if (auth != null && auth.getUserRole().equals("Admin")) {%>
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

        <div class="container">
            <h3 class="text-center">Welcome To The Users Management Panel</h3>
            <hr><!-- comment -->

            <div class="container justify-content-left">
                <div class="row justify-content-left" style="background-color: gray; padding: 20px;">
                    <div class="col-lg-4 col-md-6 mt-3">
                        <a href="addUser.jsp" class="btn btn-dark" id="addUnitButton">Add New User</a>
                    </div>


                </div>


                <div class="container justify-content-right">
                    <div class="row justify-content-right">
                        <div class="col-lg-4 col-md-6 mt-3">
                            <a href="AdminLandingPage.jsp" class="btn btn-dark">Exit</a>
                        </div>
                    </div>
                </div>

                <form action="ViewUsers" method="GET">
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
                                <th>Name</th>
                                <th>Surname</th>
                                <th>Mobile</th>
                                <th>Email</th>
                                <th>Role</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>

                        <% List<User> users = (List<User>) request.getAttribute("usersList");
                            for (User list : users) {%>
                        <tr>
                            <td><%= list.getName()%></td>
                            <td><%= list.getSurname()%></td>
                            <td><%= list.getMobileNumber()%></td>
                            <td><%= list.getEmailAddr()%></td>
                            <td><%= list.getUserRole()%></td>
                            <td><%
                                if (list.isStatus()) {
                                    out.print("Active");
                                } else {
                                    out.print("Disabled");
                                }
                                %></td>
                            <td>
                                <% if (list.isStatus()) {%>
                                <a href="DisableUserStatus?user_emailAdd=<%=list.getEmailAddr()%>" class="btn btn-danger">Disable</a>
                                <% } else {%>
                                <a href="ActivateUserStatus?user_emailAdd=<%=list.getEmailAddr()%>" class="btn btn-success">Activate</a>
                                <% }%>
                            </td>
                        </tr>
                        <% }%>
                    </table>
                </form>
            </div>     
        </div>
        <%@include file="includes/footer.jsp" %>

    </body>
</html>

<script>
    document.getElementById("addUserButton").addEventListener("click", function (event) {
        event.preventDefault();

        document.getElementById("userForm").style.display = "block";
    });


</script>



