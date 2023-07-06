
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="za.co.mie.model.*" %>
<%@page import="java.util.*"%>
<%@page import="za.co.mie.bakeryDao.BakeryDaoProductImpl"%>
<%@page import="java.sql.Connection"%>
<%@page import="za.co.mie.db.listener.DBManager"%>
<%@page import="java.text.DecimalFormat" %>

<%
    
    User auth = (User) request.getSession().getAttribute("auth");
    List<CartLineItem> shoppingCart = (ArrayList<CartLineItem>) session.getAttribute("cart-List");
    if (shoppingCart != null) {
        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryDaoProductImpl bdpi = new BakeryDaoProductImpl(con);
        shoppingCart = bdpi.getCartProducts(shoppingCart);
       double total = bdpi.getTotalCartPrice(shoppingCart);
         request.setAttribute("cart_list", shoppingCart);
         request.setAttribute("total",total);
        
    }
     DecimalFormat decimalFormat = new DecimalFormat("0.00");
     request.setAttribute("decimalFormat", decimalFormat);
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
                    <a class="nav-link" href="cart.jsp"><i class="fas fa-shopping-cart"></i>Cart <span class="badge badge-success">${cart_list.size()}</span></a>
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
            <div class="d-flex py-3"><h3>Total Price:R ${(total>0)? decimalFormat.format(total) : 0.0} </h3><a class="mx-3 btn btn-success" href="CartCheckout">Check out</a></div>
            <table class="table table-loght">
                <thead>
                    <tr>
                        <th scope="col">Product title</th>
                        <th scope="col">Product Price</th>
                        <th scope="col">Product description</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Cancel</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (shoppingCart != null) {
                            for (CartLineItem item : shoppingCart) {%>
                    <tr>
                        <td><%=item.getProduct().getProduct_title()%></td>
                        <td><%=decimalFormat.format(item.getProduct().getProductPrice())%></td>
                        <td><%=item.getProduct().getDescription()%></td>
                        <td>
                            <form action="" method="POST" class="form-inline">
                                <input type="hidden" name="id" value="<%=item.getProduct().getProduct_title()%>" class="form-input">
                                <div class="form-group d-flex justify-content-between" >
                                    <a class="btn btn-sml btn-decre" href="IncreAndDecre?action=dec&id=<%=item.getProduct().getId()%>"><i class="fas fa-minus-square"></i></a>
                                    <input type="text" name="quantity" class="form-control" value="<%=item.getQuantity()%>" readonly>
                                    <a class="btn btn-sml btn-incre" href="IncreAndDecre?action=inc&id=<%=item.getProduct().getId()%>"><i class="fas fa-plus-square"></i></a>
                                </div>
                            </form>
                        </td>
                        <td><a class=" btn btn-light" href="RemoveFromCart?id=<%=item.getProduct().getId()%>">Remove</a></td>
                    </tr>
                    <%}
                        }
                    %> 


                </tbody>

            </table>   
            <div>
                <td><a class=" btn btn-dark" href="http://localhost:8080/BakeryProject/indexPageList">Back</a></td>
            </div>

        </div>
                    <script>
    function returnToPage() {
        window.location.href = "http://localhost:8080/BakeryProject/address.jsp";
    }
</script>

        <%@include file="includes/footer.jsp" %>
    </body>
</html>
