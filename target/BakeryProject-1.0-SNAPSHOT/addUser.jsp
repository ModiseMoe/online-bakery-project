<%-- 
    Document   : addUser
    Created on : Jun 15, 2023, 10:11:40 AM
    Author     : Train
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                max-width: 1000px;
                margin: 0 auto;


            }
        </style>
    </head>
    <body>
       <section class="vh-100">
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

                  <form method="POST"  action="AddUser">

                  <div class="d-flex align-items-center mb-3 pb-1">
                    <i class="fas fa-cubes fa-2x me-3" style="color: #818589 "></i>
                    <span class="h1 fw-bold mb-0">"To Pie For" Bakery</span>
                  </div>

                  <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Register an account</h5>

                  <div class="form-outline mb-4">
                      <input type="text"  name="username" id="form2Example17" class="form-control form-control-lg" >
                    <label class="form-label" for="form2Example17">Username</label>
                  </div>

                  <div class="form-outline mb-4">
                    <input type="text" name="surname" id="form2Example27" class="form-control form-control-lg"/>
                    <label class="form-label" for="form2Example27">Surname</label>
                  </div>
                  
                   <div class="form-outline mb-4">
                       <input type="password" name="password" id="form2Example27" class="form-control form-control-lg"  required/>
                    <label class="form-label" for="form2Example27">Password</label>
                  </div>
                  
                   <div class="form-outline mb-4">
                       <input type="text" name="emailAddr"  id="form2Example27" class="form-control form-control-lg"  required/>
                    <label class="form-label" for="form2Example27">Email address</label>
                  </div>
                  
                   <div class="form-outline mb-4">
                       <input type="text" name="mobileNum" id="form2Example27" class="form-control form-control-lg" />
                    <label class="form-label" for="form2Example27">Contact no</label>
                  </div>
                  
                   <div class="form-outline mb-4">
                    <input type="text" name="idNo" id="form2Example27" class="form-control form-control-lg" />
                    <label class="form-label" for="form2Example27">ID No</label>
                  </div>
                  <select name="userRole" class="select form-control-lg">
                    <option value="1" disabled>Choose role</option>
                    <option value="User">User</option>
                    <option value="Admin">Admin</option>
                  </select>
                  <label class="form-label select-label">Choose role</label>

                  <div class="pt-1 mb-4">
                     <input  class="btn btn-dark btn-lg btn-block"  type="submit" value="Submit">
                      <a href="ViewUsers" class="btn btn-dark">Cancel</a>
                  </div>
                  
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
            function returnToPage(){
                window.location.href = "http://localhost:8080/BakeryProject/address.jsp";
            }
        </script>
         <%@include file="includes/footer.jsp" %>
    </body>
</html>
