<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="za.co.mie.model.*" %>
<%@page import="java.util.List"%>
<%
    Order order = (Order) request.getAttribute("order-for-id");
    User auth = (User) request.getSession().getAttribute("auth");

//        response.sendRedirect("index.jsp");

%>    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"


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
        .card{
            max-width: 1000px; /* Adjust the max-width value as needed */
            margin: 0 auto; /* Center the card horizontally */
            text-align: center; /* Center the card's contents */
            padding: 20px;
            background-color: rgba(255, 255, 255, 0.6);

        }
        .card-body{
            margin: 0 auto; /* Center the card horizontally */
            text-align: center; /* Center the card's contents */
            padding: 20px;
            max-width: 1000px;
        }
        .col-md-6{
            margin: 0 auto;
            text-align: center;
            max-width: 1000px;
         
        }
        .form-outline{
             max-width: 1000px;
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

            <%                    if (auth != null) {%>
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

<section class="p-4 p-md-5" >
    <div class="row d-flex justify-content-center">
        <div class="col-md-8 col-lg-5 col-xl-10">
            <div class="card rounded-3">
                <div class="card-body p-4">
                    <div class="row">
                        <div class="col-md-6">
                            <form method="POST" action="AddressAndPurchase">
                                <h3 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Delivery Details</h3>
                                <div class="form-outline mb-1">
                                    <% List<Address> addressList = (List<Address>) request.getAttribute("addressList");

                                    %>
                                    <div class="row">
                                        <div class="col">
                                            <input type="text" name="address_details" id="form2Example27" class="form-control form-control-lg" required/>
                                            <input type="hidden" name="order_id" value="<%=order.getOrder_id()%>" id="form2Example27" class="form-control form-control-lg" />
                                            <label class="form-label" for="form2Example27">Address Line1</label>
                                        </div>
                                        <div class="col">
                                            <select class="form-select form-select-lg" name="address_details" id="addressSelect">
                                                <option value="addressLine" selected disabled>address Line</option>
                                                <% for (Address lst : addressList) {%>
                                                <option value="<%= lst.getAddress_id()%>"><%= lst.getAddress_details()%></option>
                                                <% } %>
                                            </select>

                                        </div>
                                    </div>
                                </div>

                                <div class="form-outline mb-1">
                                    <div class="row">
                                        <div class="col">
                                            <input type="text" name="city" id="form2Example28" class="form-control form-control-lg" required/>
                                            <label class="form-label" for="form2Example28">City</label>
                                        </div>
                                        <div class="col">
                                            <select class="form-select form-select-lg" name="city" id="addressSelect2">
                                                <option value="city" selected disabled>City</option>
                                                <% for (Address lst : addressList) {%>
                                                <option value="<%=lst.getAddress_id()%>"><%=lst.getCity()%></option>
                                                <% }%>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-outline mb-1">
                                    <div class="row">
                                        <div class="col">
                                            <input type="text" name="postal_code" id="form2Example29" class="form-control form-control-lg" required/>
                                            <label class="form-label" for="form2Example29">Postal Code</label>
                                        </div>
                                        <div class="col">
                                            <select class="form-select form-select-lg" name="address_details" id="addressSelect3">
                                                <option value="Postal  code" selected disabled>Postal code</option>
                                                <% for (Address lst : addressList) {%>
                                                <option value="<%=lst.getAddress_id()%>"><%=lst.getPostal_code()%></option>
                                                <% }%>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-outline mb-1">
                                    <div class="row">
                                        <div class="col">
                                            <input type="text" name="country" id="form2Example30" class="form-control form-control-lg" required/>
                                            <label class="form-label" for="form2Example30">Country</label>
                                        </div>
                                        <div class="col">
                                            <select class="form-select form-select-lg" name="address_details" id="addressSelect4">
                                                <option value="Country" selected disabled>country</option>
                                                <% for (Address lst : addressList) {%>
                                                <option value="<%=lst.getAddress_id()%>"><%=lst.getCountry()%></option>
                                                <% }%>
                                            </select>
                                        </div>
                                    </div>
                                </div>



                                <h3 class="fw-normal mb-3 pb-3">Payment Details</h3>

                                <p class="fw-bold mb-4">Enter payment details:</p>
                                <img class="img-fluid" src="https://img.icons8.com/color/48/000000/mastercard-logo.png" />
                                <div class="form-outline mb-1">

                                    <input type="text" name="cardHolder_name" id="formControlLgXsd" class="form-control form-control-lg" placeholder="Enter your name and surname" />
                                    <label class="form-label" for="formControlLgXsd">Cardholder's Name</label>
                                </div>
                                <div class="row mb-4">
                                    <div class="col-7">
                                        <div class="form-outline">
                                            <input type="text" name="cardNumber" id="formControlLgXM" class="form-control form-control-lg" placeholder="1234 5678 1234 5678" />
                                            <label class="form-label" for="formControlLgXM">Card Number</label>
                                        </div>
                                    </div>
                                    <div class="col-3">
                                        <div class="form-outline">
                                            <input type="text" name="expire" id="formControlLgExpk" class="form-control form-control-lg" placeholder="MM/YYYY" />
                                            <label class="form-label" for="formControlLgExpk">Expire</label>
                                        </div>
                                    </div>
                                    <div class="col-2">
                                        <div class="form-outline">
                                            <input type="text" name="cvv" id="formControlLgcvv" class="form-control form-control-lg" placeholder="Cvv" />
                                            <label class="form-label" for="formControlLgcvv">Cvv</label>
                                        </div>
                                    </div>
                                </div>
                                <button class="btn btn-success btn-lg btn-block" type="submit">Make Purchase</button>
                                <a href="ShowOrders" class="btn btn-success btn-lg btn-block">Cancel</a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<script>
    document.getElementById('addressSelect').addEventListener('change', function () {
        var selectedOption = this.options[this.selectedIndex];
        var addressDetails = selectedOption.text;
        document.getElementById('form2Example27').value = addressDetails;
    });
</script>

<script>

    document.getElementById('addressSelect2').addEventListener('change', function () {
        var selectedOption = this.options[this.selectedIndex];
        var city = selectedOption.text;
        document.getElementById('form2Example28').value = city;
    });
</script>
<script>
    document.getElementById('addressSelect3').addEventListener('change', function () {
        var selectedOption = this.options[this.selectedIndex];
        var postalCode = selectedOption.text;
        document.getElementById('form2Example29').value = postalCode;
    });
</script>

<script>

    document.getElementById('addressSelect4').addEventListener('change', function () {
        var selectedOption = this.options[this.selectedIndex];
        var country = selectedOption.text;
        document.getElementById('form2Example30').value = country;
    });
</script>                    


<center>
    <input type="hidden" id="pro" name="pro" value="dbman" >
</center>
<style>
    .custom-col {
        width: 70%; /* Adjust the width value as needed */
    }
</style>


<%@include file="includes/footer.jsp" %>
</body>
</html>
