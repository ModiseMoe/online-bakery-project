<%-- 
    Document   : categoryFom
    Created on : May 30, 2023, 7:57:55 PM
    Author     : mdice
--%>
<%@page import="za.co.mie.model.*" %>
<%@page import="java.util.List"%>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    Category data = (Category) request.getAttribute("data");
//        response.sendRedirect("index.jsp");
    
%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
         <%@include file="includes/header.jsp" %>
          <style>
            body {

                background-image: url("images/5.jpg");
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
  <a class="navbar-brand" href="AdminLandingPage.jsp">"To Pie For" Bakery</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav ms-auto">
     
       <%
        if(auth != null && auth.getUserRole().equals("Admin")){%>
         <li class="nav-item">
             
            <a class="nav-link" href="#"><i class="fas fa-user"></i>user</a>
      </li> 
        
         <li class="nav-item">
        <a class="nav-link" href="Logout">Logout</a>
      </li>        
        
        <%}else{%>
            <li class="nav-item">
        <a class="nav-link" href="login.jsp">Login</a>
      </li>
      <%}
        %>
    </ul>
  </div>
</nav>
    
    
    <div class="contsiner col-md-5">
        <div class="card">
            <div class="card-body">
                <form action="UpdateCategory" method="POST">
                <caption>Edit A Category</caption>
                
                <fieldset class="form-group">
                    <label>Category Name</label>
                    <input type="text" name="category_name" value="<%=data.getCategoryName() %>" class="form-control">
                    <input type="hidden" name="cat_id" value="<%=data.getId()%>" class="form-control">
                </fieldset>
                
                <button type="submit" class="btn btn-dark" >Save</button>
                <a href="ShowList" class="btn btn-dark">Cancel</a>
                </form>
                
            </div>
            
        </div>
    </div>
    
    
    
    
    </body>
</html>
