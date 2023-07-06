
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
                max-width: 700px; 
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
            <div class="row d-flex justify-content-center">
                <div class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
                    <div class="row">
                        <div class="col-xs-6 col-sm-6 col-md-6">
                            <img src="to-pie-for-logo.png" class="card-img-top" style="aspect-ratio:1 / 1" />
                        </div>
                        <div class="col-xs-6 col-sm-6 col-md-6 text-right">
                            <p>
                                <em>Date:<%= new java.util.Date()%></em>
                            </p>

                        </div>
                    </div>
                    <div class="row mb-4">
                        <div class="text-center">
                            <h1>Invoice</h1>
                        </div>
                        </span>
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Product title</th>
                                    <th>Quantity</th>
                                    <th class="text-center">Price</th>
                                    <th class="text-center">Total</th>
                                    <th class="text-center">Order ID</th>
                                </tr>
                            </thead>
                            <% DecimalFormat decimalFormat = new DecimalFormat("0.00");
                                List<OrderItem> orderItem = (List<OrderItem>) request.getAttribute("orderItem");
                                double subtotal = 0.0;
                                double taxAmount = 0.0;
                                double taxRate = 0.15;
                                double total = 0.0;
                                int orderId = 0;

                                for (OrderItem list : orderItem) {
                                    double productTotal = list.getUnitPrice() * list.getProductQuantity();
                                    subtotal += productTotal;
                                    taxAmount = subtotal * taxRate;
                                    total = subtotal + taxAmount;
                                    orderId = list.getOrder_id();
                            %>
                            <tr>
                                <td class="col-md-9"><em><%=list.getProduct_title()%></em></h4></td>
                                <td class="col-md-1" style="text-align: center"><%=list.getProductQuantity()%></td>
                                <td class="col-md-1 text-center">R<%=decimalFormat.format(list.getUnitPrice())%></td>
                                <td class="col-md-1 text-center">R<%=decimalFormat.format(productTotal)%></td>
                                <td class="col-md-1 text-center">#<%=list.getOrder_id()%></td>

                            </tr>
                            <%}%>
                            <tr>
                                <td>   </td>
                                <td>   </td>
                                <td class="text-right">
                                    <p>
                                        <strong>Subtotal: </strong>
                                    </p>

                                    <p>
                                        <strong>Tax: </strong>
                                    </p>
                                </td>
                                <td class="text-center">
                                    <p>
                                        <strong>R<%=decimalFormat.format(subtotal)%></strong>
                                    </p>
                                    <p>
                                        <strong>R<%=decimalFormat.format(taxAmount)%></strong>
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <td>   </td>
                                <td>   </td>
                                <td class="text-right"><h4><strong>Total: </strong></h4></td>
                                <td class="text-center text-danger"><h4><strong>R<%=decimalFormat.format(total)%></strong></h4></td>
                            </tr>
                        </table>

                        <button type="button" onclick="redirectToPage()" class="btn btn-success btn-lg btn-block mb-1">
                            Pay Now   <span class="glyphicon glyphicon-chevron-right"></span>
                        </button>
                        <button type="button" onclick="returnToPage()" class="btn btn-success btn-lg btn-block ">
                            Cancel   <span class="glyphicon glyphicon-chevron-right"></span>
                        </button>
                        </td>
                    </div>
                </div>
            </div>   
        </div>                 

        <script>
            function returnToPage() {
                window.location.href = "http://localhost:8080/BakeryProject/ShowOrders";
            }
            function redirectToPage() {
                window.location.href = "http://localhost:8080/BakeryProject/GetSingleOrder?order_id=<%=orderId%>";
            }
        </script>
        <%@include file="includes/footer.jsp" %>
    </body>
</html>
