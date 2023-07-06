


<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="indexPageList">"To Pie For" BAKERY</a>
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
        if(auth != null){%>
         <li class="nav-item">
        <a class="nav-link" href="orders.jsp"><i class="fas fa-bags-shopping"></i>Orders</a>
      </li> 
       <li class="nav-item">
           <a class="nav-link" href=""><i class="fas fa-user"></i><%= auth.getName() %></a>
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
