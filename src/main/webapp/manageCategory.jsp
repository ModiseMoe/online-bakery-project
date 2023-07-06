<%-- 
    Document   : adminPage
    Created on : May 29, 2023, 5:09:26 PM
    Author     : mdice
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="za.co.mie.model.User" %>
<%@page import="za.co.mie.model.Category" %>
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
        <div>
            <div class="row">
                <div class="container">
                    <h3 class="text-center">Welcome to the Category Panel</h3>
                    <hr><!-- comment -->

                    <div class="container justify-content-left">
                        <div class="row justify-content-left">
                            <div class="col-lg-4 col-md-6 mt-3 ">
                                <a href="addCategoryForm.jsp" class="btn btn-dark">Add New Category</a>
                            </div>
                        </div>
                  
                    <div class="container justify-content-right ">
                        <div class="row justify-content-right">
                            <div class="col-lg-4 col-md-6 mt-3 ">
                                <a href="AdminLandingPage.jsp" class="btn btn-dark">Exit</a>
                            </div>
                        </div>
                    </div>

                    <form action="showList" method="GET">

                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Category name</th>
                                    <th>Status</th>
                                    <th>Edit</th>
                                    <th>action</th>



                                </tr>
                            </thead>

                            <%
                                List<Category> myCatList = (List<Category>) request.getAttribute("categoryList");
                                for (Category list : myCatList) {%>

                            <tr>
                                <td><%= list.getCategoryName()%></td>
                                <td><%
                                    if (list.getStatus()) {
                                        out.print("Active");
                                    } else {
                                        out.print("Disabled");
                                    }
                                    %></td>
                                <td> <a href="EditCategory?cat_id=<%=list.getId()%>" class="btn btn-dark"><i class="fas fa-pen"></i></a></td>
                                <td>
                                    <% if (list.getStatus()) {%>
                                    <a href="DisableCategoryStatus?cat_id=<%=list.getId()%>" class="btn btn-danger">Disable</a>
                                    <% } else {%>
                                    <a href="ActivateCategoryStatus?cat_id=<%=list.getId()%>" class="btn btn-success">Activate</a>
                                    <% } %>
                                </td>
                            </tr>

                            <%}%>


                        </table>
                    </form>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function toggleCategoryStatus(categoryId, currentStatus) {
                var newStatus = !currentStatus;
                var url = newStatus ? "ActivateCategoryStatus" : "DisableCategoryStatus";
                url += "?cat_id=" + categoryId;
                window.location.href = url;
            }


        </script>              

        <%@include file="includes/footer.jsp" %>

    </body>
</html>
