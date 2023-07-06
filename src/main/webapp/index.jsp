<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="za.co.mie.model.*" %>
<%@page import="java.util.List"%>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {

//        response.sendRedirect("index.jsp");
    }
%>    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="includes/header.jsp" %>
        <link href="https://fonts.googleapis.com/css2?family=Your+Selected+Font&display=swap" rel="stylesheet">
        <style>
            body{

                background-image: url("background2.jpg");
                backdrop-filter: blur(2px);
                background-repeat: no-repeat;
                background-size: cover;
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
            }

            .card {
                background-color: rgba(255, 255, 255, 0.6);
                border-radius: 15px;
                max-width: 900px;
               
                margin: 200px auto;
                padding: 20px;
            }

            .card-title {
                font-family: 'Cabin', sans-serif;
                font-size: 40px;
                font-weight: bold;
                margin-bottom: 10px;
            }

            .card-text {
                font-family: 'Dancing Script', sans-serif;
                font-size: 18px;
                margin-bottom: 20px;
            }

            .btn {
                font-size: 18px;
                padding: 10px 20px;
                text-decoration: none;
                background-color: #000;
                color: #fff;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }

            .btn:hover {
                background-color: #333;
            }

            .container {
                display: flex;
                justify-content: flex-start;
                align-items: center;
                height: 80vh;
            }

            .col-lg-4 {
                text-align: center;
                margin: 0 auto;
            }

            .col-lg-4 img {
                max-width: 100%;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            }
            .btn-dark.transparent-border {
                background-color: transparent;
                color: #343a40;
                border: 1px solid #343a40;
            }

            .btn-dark.transparent-border:hover {
                background-color: #343a40;
                color: #ffffff;
            }
        </style>

    </head>
    <body>
        <%@include file="includes/navbar.jsp" %>

        <div class="card text-center">
            <div class="card-body">
                <h3 class="card-title">Welcome To - To Pie For bakery</h3>
                <p class="card-text">A Journey of Sweetness: Enter Our Bakery Wonderland</p>
                <a href="indexPageList" class="btn btn-dark">Shop Products</a>
                <a href="login.jsp" class="btn btn-dark transparent-border">login</a>
            </div>
        </div>

    <section>
        <div class="container">

        </div>
    </section>




</form>
<script>
    function returnToPage() {
        window.location.href = "http://localhost:8080/BakeryProject/address.jsp";
    }
</script>
<%@include file="includes/footer.jsp" %>
</body>
</html>
