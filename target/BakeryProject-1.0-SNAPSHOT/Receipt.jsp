<%-- 
    Document   : allIngredientsReport
    Created on : Jun 15, 2023, 1:49:57 PM
    Author     : Train
--%>

<%@ page import="java.util.List" %>
<%@ page import="za.co.mie.model.Ingridient" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Ingredients Report</title>
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
            <h1>Download Receipt</h1>

            <form action="InvoicePDF" method="post">
                <input type="submit" value="Download Receipt">
            </form>

            
        </div> 
    </body>
</html>


