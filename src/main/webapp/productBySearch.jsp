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

            .card {
                background-color: rgba(255, 255, 255, 0.6);
                border-radius: 10px;
                max-width: 300px;
                margin: 100px auto;
                padding: 20px;
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


    <section>
        <div class="container my-5">
            <header class="mb-4">
                <div class="container">
                    <!-- Navbar -->
                    <nav class="navbar navbar-expand-lg navbar-dark mt-3 mb-5 shadow p-2" style="background-color:currentColor">
                        <!-- Container wrapper -->
                        <div class="container-fluid">

                            <!-- Navbar brand -->
                            <a class="navbar-brand" href="#">Categories:</a>
                            <a class="navbar-brand" href="indexPageList">All</a>
                            <!-- Toggle button -->
                            <button 
                                class="navbar-toggler" 
                                type="button" 
                                data-mdb-toggle="collapse" 
                                data-mdb-target="#navbarSupportedContent2" 
                                aria-controls="navbarSupportedContent2" 
                                aria-expanded="false" 
                                aria-label="Toggle navigation">
                                <i class="fas fa-bars"></i>
                            </button>

                            <!-- Collapsible wrapper -->
                            <div class="collapse navbar-collapse" id="navbarSupportedContent2">
                                <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                                    <!-- Link -->
                                    <li class="nav-item acitve">
                                        <% List<Category> cat3 = (List<Category>) request.getAttribute("cat3"); %>
                                        <fieldset class="form-group">
                                            <div class="container mt-4">
                                                <div class="dropdown">
                                                    <form id="categoryForm" action="FllterByCat" method="GET">
                                                        <select name="catSelected" class="select" onchange="submitForm()">
                                                            <option value="category" selected disabled>Select a category</option>
                                                            <% for (Category list : cat3) {%>
                                                            <option value="<%=list.getId()%>"><%=list.getCategoryName()%></option>
                                                            <% }%>
                                                        </select>
                                                    </form>
                                                </div>
                                            </div>
                                        </fieldset>

                                    </li>
                                </ul>

                                <!-- Search -->
                                <form id="searchForm" class="w-auto py-1" style="max-width: 12rem" action="SearchProduct" method="GET">
                                    <input type="search" id="searchInput"  name="product_title" class="form-control rounded-0" placeholder="Search" aria-label="Search">
                                </form>

                            </div>
                        </div>
                    </nav>  

                    <style>
                        .navbar-nav .nav-item {
                            margin-right: 10px;
                        }

                        .nav-link text-white{
                            margin-bottom: 100px
                        }

                        .form-group {
                            margin-bottom: 0;
                        }

                        .dropdown {
                            display: inline-block;
                        }

                        .dropdown select {
                            display: block;
                            width: 100%;
                            padding: 0.375rem 0.75rem;
                            font-size: 1rem;
                            font-weight: 400;
                            line-height: 1.5;
                            color: #212529;
                            background-color: #fff;
                            background-clip: padding-box;
                            border: 1px solid #ced4da;
                            border-radius: 0.25rem;
                            transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
                            margin-bottom: 20px;
                        }

                        .select {
                            width: 200px;
                        }

                        .accordion-item {
                            margin-bottom: 10px;
                        }

                        .accordion-heading {
                            cursor: pointer;
                            background-color: #f5f5f5;
                            padding: 10px;
                            border: none; /* Remove the border */
                            border-radius: 5px;
                            display: flex;
                            align-items: center;
                            justify-content: space-between;
                        }

                        .accordion-icon {
                            margin-right: 10px;
                        }

                        .accordion-content {
                            display: none;
                            background-color: #fff;
                            padding: 10px;
                            border: none; /* Remove the border */
                            border-radius: 5px;
                        }

                        .accordion-content.show {
                            display: block;
                        }
                    </style>


            </header>
            <form action="SearchProduct" method="GET">
                <div class="row">
                    <%  List<Product> searchResults = (List<Product>) request.getAttribute("search");
                        for (Product p : searchResults) {%> 


                    <div class="col-lg-3 col-md-6 col-sm-6 d-flex">
                        <div class="card w-100 my-2 shadow-2-strong">
                            <img src="bakeryProducts/<%=p.getImage()%>" class="card-img-top" style="aspect-ratio:1 / 1" />
                            <div class="card-body d-flex flex-column">
                                <h5 class="card-title"><%=p.getProduct_title()%></h5>
                                <h6 class="card-text">Price R<%=p.getProductPrice()%></h6>
                                <div class="accordion">
                                    <div class="accordion-item">
                                        <h6 class="accordion-heading"><span class="accordion-icon">
                                                <i class="fas fa-info-circle"></i>
                                            </span>Show details</h6>
                                        <div class="accordion-content">
                                            <p class="card-text"><%=p.getDescription()%></p>
                                            <p class="card-text"><%=p.getProductWarnigs()%></p>
                                        </div>
                                    </div>
                                </div>

                                <div class="card-footer d-flex align-items-end pt-3 px-0 pb-0 mt-auto">
                                    <a href="AddToCart?product_id=<%=p.getId()%>" class="btn btn-dark shadow-0 me-1">Add to cart</a>

                                </div>
                            </div>
                        </div>
                    </div>

                    <%}%>

                </div>
            </form>
        </div>
    </section>

</div>
</nav>



</form>
<script>
    function submitForm() {
        document.getElementById("categoryForm").submit();
    }
    // Get all accordion headings
    const accordionHeadings = document.querySelectorAll('.accordion-heading');

// Add click event listeners to each heading
    accordionHeadings.forEach(heading => {
        heading.addEventListener('click', function () {
            // Toggle the visibility of the content when heading is clicked
            const content = this.nextElementSibling;
            content.style.display = content.style.display === 'none' ? 'block' : 'none';
        });
    });
</script>

<script>
    var typingTimer;
    var doneTypingInterval = 500; // milliseconds

    // Event handler for input event
    document.getElementById('searchInput').addEventListener('input', function () {
        clearTimeout(typingTimer);
        typingTimer = setTimeout(submitForm, doneTypingInterval);
    });

    // Function to submit the form
    function submitForm() {
        document.getElementById("searchForm").submit();
    }
</script>
<%@include file="includes/footer.jsp" %>
</body>
</html>

