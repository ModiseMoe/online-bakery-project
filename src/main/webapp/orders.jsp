
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="za.co.mie.model.*" %>
<%@page import="java.util.*"%>
<%@page import="za.co.mie.bakeryDao.BakeryDaoProductImpl"%>
<%@page import="java.sql.Connection"%>
<%@page import="za.co.mie.db.listener.DBManager"%>
<%@page import="java.text.DecimalFormat" %>

<%

    User auth = (User) request.getSession().getAttribute("auth");
%>  
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

            .container {
                margin-top: 50px;
                background-color: rgba(255, 255, 255, 0.6);

            }
            .my-table {
                margin-top: 50px;
            }

            .my-table .table {
                background-color: rgba(255, 255, 255, 0.6);
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




        <div class="container">
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">Date</th>
                        <th scope="col">User Id</th>
                        <th scope="col">Payment Status</th>
                        <th scope="col">Proceed to payment</th>
                        <th scope="col">Cancel Order</th>
                    </tr>
                </thead>
                <tbody>
                    <% List<Order> orders = (List<Order>) request.getAttribute("orders");
                        for (Order lst : orders) {%>
                    <tr>
                        <td><%= lst.getOrderDate()%></td>
                        <td><%= lst.getUserId()%></td>
                        <td><% if (lst.isPayment_status()) {
                                out.print("Payment Successfull");
                            } else {
                                out.print("Payment Pending");
                            }%> </td>
                        <td>
                            <% if (lst.isPayment_status()) { %>
                            <a href="#" class=" btn btn-warning disabled ">Proceed to payment</a>
                            <% } else {%>
                            <a href="ShowOrderItems?order_id=<%= lst.getOrder_id()%>" class="btn btn-success">Proceed to payment</a>
                            <% } %>
                        </td>
                        <td><a href="DisableOrder?order_id=<%=lst.getOrder_id()%>" class="btn btn-dark">Cancel Order</a></td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
            <div>
                <a class="btn btn-dark" href="http://localhost:8080/BakeryProject/indexPageList">Back</a>
            </div>
        </div>


        <%@include file="includes/footer.jsp" %>
    </body>
</html>
