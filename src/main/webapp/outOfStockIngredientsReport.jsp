<%-- 
    Document   : outOfStockIngredientsReport
    Created on : Jun 20, 2023, 9:33:45 AM
    Author     : Train
--%>

<%@ page import="java.util.List" %>
<%@ page import="za.co.mie.model.Ingridient" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Out Of Stock Ingredients Report</title>
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
            
            .container{
                background-color: rgba(255, 255, 255, 0.6);
            }
        </style>
    </head>
    <body>
        <div>    
            <h1>Out Of Stock Ingredients Report</h1>

            <form action="OutOfStockIngredientsReport" method="post">
                <input type="submit" value="Download Report">
                  <a href="reportDashboard.jsp" class="btn btn-dark">back</a>
            </form>
           

            <table>
                <tr>
                    <th>Name</th>
                    <th>Quantity on Hand</th>
                    <th>Minimum Stock Quantity</th>
                </tr>
                <%
                    List<Ingridient> ingredientList = (List<Ingridient>) request.getAttribute("ingredientsList");
                    for (Ingridient ingredient : ingredientList) {
                %>
                <tr>
                    <td><%= ingredient.getIngridientsName()%></td>
                    <td><%= ingredient.getQuantityOnHand()%></td>
                    <td><%= ingredient.getMinimumStockQuantity()%></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div> 
    </body>
</html>
