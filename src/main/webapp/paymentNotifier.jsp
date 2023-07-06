<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="za.co.mie.model.*" %>
<%@page import="java.util.*"%>
<%@page import="za.co.mie.bakeryDao.BakeryDaoProductImpl"%>
<%@page import="java.sql.Connection"%>
<%@page import="za.co.mie.db.listener.DBManager"%>
<%@page import="java.text.DecimalFormat" %>

<%

    User auth = (User) request.getSession().getAttribute("auth");
    Order one_order = (Order) request.getAttribute("one_order");
%>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://fonts.googleapis.com/css?family=Nunito+Sans:400,400i,700,900&display=swap" rel="stylesheet">
        <style>
            body {
                background-image: url("background2.jpg");
                backdrop-filter: blur(5px);
                text-align: center;
                padding: 40px 0;
                background: #EBF0F5;
            }
            h1 {
                color: #88B04B;
                font-family: "Nunito Sans", "Helvetica Neue", sans-serif;
                font-weight: 900;
                font-size: 40px;
                margin-bottom: 10px;
            }
            p {
                color: #404F5E;
                font-family: "Nunito Sans", "Helvetica Neue", sans-serif;
                font-size:20px;
                margin: 0;
            }
            i {
                color: #9ABC66;
                font-size: 100px;
                line-height: 200px;
                margin-left:-15px;
            }
            .card {
                background-color: rgba(255, 255, 255, 0.6);
                background: white;
                padding: 60px;
                border-radius: 4px;
                box-shadow: 0 2px 3px #C8D0D8;
                display: inline-block;
                margin: 0 auto;
            }
        </style>
    </head>
    <body>
        <div class="card">
            <% if (one_order.isPayment_status()) {%> 
            <div style="border-radius:200px; height:200px; width:200px; background: #F8FAF5; margin:0 auto;">
               <i class="fa-solid fa-check" style="color: #00ff00;"> (: </i>
            </div>
            <h1>Success</h1> 
            <p>Your payment for order#<%=one_order.getOrder_id()%> was successful<br/>Your order will be delivered soon</p>
            <a href="indexPageList" >click here to continue shopping</a>
        </div>
        <% } else {%>
        <div style="border-radius:200px; height:200px; width:200px; background: #F8FAF5; margin:0 auto;">
            <i class="fa-solid fa-xmark" style="color: #ff0000;">:(</i>
        </div>
        <h1>Un-Successful</h1> 
        <p>Your payment for order#<%=one_order.getOrder_id()%>was not successful<br/>Please check that you have sufficient funds</p>
        <a href="indexPageList" >click here to continue shopping</a>
        <%}%>
    </body>
</html>
