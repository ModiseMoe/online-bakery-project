<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="za.co.mie.model.*" %>
<%@page import="java.util.List"%>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    List<CartLineItem> shoppingCart = (ArrayList<CartLineItem>) session.getAttribute("cart-List");
     request.setAttribute("cart_list", shoppingCart);
%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="includes/header.jsp" %>
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
                    <a class="nav-link" href="cart.jsp"><i class="fas fa-shopping-cart"></i>Cart <span class="badge badge-danger">${cart_list.size()}</span></a>
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
            
            
    <section class="vh-100" style="background-image: url('bread.jpg') ">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col col-xl-10">
                    <div class="card" style="border-radius: 1rem;">
                        <div class="row g-0">
                            <div class="col-md-6 col-lg-5 d-flex align-items-center justify-content-center">
                                <img src="to-pie-for-logo.png"
                                     alt="login form" class="img-fluid" style="border-radius: 1rem 0 0 1rem;" />
                            </div>
                            <div class="col-md-6 col-lg-7 d-flex align-items-center">
                                <div class="card-body p-4 p-lg-5 text-black">

                                    <form method="POST"  action="UserRegistration">

                                        <div class="d-flex align-items-center mb-3 pb-1">
                                            <i class="fas fa-cubes fa-2x me-3" style="color: #818589 "></i>
                                            <span class="h1 fw-bold mb-0">"To Pie For" Bakery</span>
                                        </div>

                                        <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Register an account</h5>

                                        <div class="form-outline mb-1">
                                            <input type="text"  name="username" id="form2Example17" class="form-control form-control-lg" required >
                                            <label class="form-label" for="form2Example17">Username</label>
                                        </div>

                                        <div class="form-outline mb-1">
                                            <input type="text" name="surname" id="form2Example27" class="form-control form-control-lg" required/>
                                            <label class="form-label" for="form2Example27">Surname</label>
                                        </div>

                                        <div class="form-outline mb-1">
                                            <input type="password" name="password" id="form2Example27" class="form-control form-control-lg"  required/>
                                            <label class="form-label" for="form2Example27">Password</label>
                                        </div>

                                        <div class="form-outline mb-1">
                                            <input type="text" name="emailAddr"  id="form2Example27" class="form-control form-control-lg"  required/>
                                            <label class="form-label" for="form2Example27">Email address</label>
                                        </div>

                                        <div class="form-outline mb-2">
                                            <input type="text" name="mobileNum" id="form2Example27" class="form-control form-control-lg" required />
                                            <label class="form-label" for="form2Example27">Contact no</label>
                                        </div>

                                        <div class="form-outline mb-1">
                                            <input type="text" name="idNo" id="form2Example27" class="form-control form-control-lg"required />
                                            <label class="form-label" for="form2Example27">ID No</label>
                                        </div>
                                        <select name="userRole" class="select form-control-lg">
                                            <option value="1" disabled>Choose role</option>
                                            <option value="User">User</option>
                                            
                                        </select>
                                        <label class="form-label select-label">Choose role</label>

                                        <div class="pt-1 mb-4">
                                            <input  class="btn btn-dark btn-lg btn-block"  type="submit" value="Submit">
                                        </div>

                                        <p class="mb-5 pb-lg-2" style="color: #393f81;">Already have an account? <a href="http://localhost:8080/BakeryProject/login.jsp"
                                                                                                                    style="color: #393f81;">Login here</a></p>
                                        <center>
                                            <input type="hidden" id="pro" name="pro" value="dbman" >
                                        </center>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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
