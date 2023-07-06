<%-- 
    Document   : processedOrdersReport
    Created on : Jun 20, 2023, 1:55:12 PM
    Author     : Train
--%>

<%@ page import="java.util.List" %>
<%@ page import="za.co.mie.model.Order" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Processed Orders Report</title>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }

            th, td {
                padding: 8px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #f2f2f2;
            }

            body {
                background: url("images/4.webp");
                background-repeat: no-repeat;
                background-size: cover;
            }
        </style>
    </head>
    <body>
        <div>    
            <h1>Processed Orders Report</h1>

            <form action="ProcessedOrdersReport" method="post">
                <input type="submit" value="Download Report">
               <a href="reportDashboard.jsp" class="btn btn-dark">back</a>
            </form>

            <table>
                <tr>
                    <th>Order Date</th>
                    <th>Customer ID</th>
                    <th>Order Status</th>
                    <th>Payment Status</th>
                </tr>
                <%
                    List<Order> orderList = (List<Order>) request.getAttribute("processedOrderList");
                    for (Order order : orderList) {
                %>
                <tr>
                    <td><%=order.getOrderDate()%></td>
                    <td><%=order.getUserId()%></td>
                    <td><%
                        if (order.isStatus()) {
                            out.print("Active");
                        } else {
                            out.print("Cancelled");
                        }

                        %></td>
                    <td><%                        if (order.isPayment_status()) {
                            out.print("Payed");
                        } else {
                            out.print("Pending..");
                        }
                        %></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div> 
    </body>
</html>


