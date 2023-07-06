<%-- 
    Document   : addRecipeForm
    Created on : Jun 9, 2023, 9:55:44 AM
    Author     : Train
--%>

<%@page import="java.util.List"%>
<%@page import="za.co.mie.model.*" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {

//        response.sendRedirect("index.jsp");
    }
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
            
            .in-Instruction{
                 height:180px;
                 width: 500px
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
                        if (auth != null && auth.getUserRole().equals("Admin")) {%>
                    <li class="nav-item">

                        <a class="nav-link" href="#"><i class="fas fa-user"></i>user</a>
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
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <h3>Add Recipe</h3>
                    <div class="form">
                        <form id="myFormAdd" action="AddRecipe" method="POST">
                            <label>Name</label><br>
                            <input class="in" type="text" name="recipe_name" required><br>
                            <label>Instructions</label><br>
                            <input class="in-Instruction" type="text" name="description" required><br>
                            <label>List of Ingredients</label><br>
                            <div id="ingredientList"></div>

                            <div id="popupForm">
                                <label>Ingredients</label><br>
                                <select id="ingredientDropdown">
                                    <option value="">None</option>

                                    <% List<Ingridient> ingredientList = (List<Ingridient>) request.getAttribute("ingredientList");
                                        if (ingredientList != null && !ingredientList.isEmpty()) {
                                            for (Ingridient i : ingredientList) {%>
                                    <option value="<%=i.getIngredientId()%>"><%=i.getIngridientsName()%></option>
                                    <% }
                                        }%>
                                </select>

                                <label>Quantity</label><br>
                                <input class="in" type="number" name="quantityInput" id="quantityInput" placeholder="quantityInput" required><br>

                                <div class="saveButton">
                                    <button type="button" id="addIngredientBtn" onclick="saveIngredient()">Add Ingredient</button>
                                </div>
                            </div>

                        </form>

                        <div id="popup" class="form-container">
                            <div class="con">
                                <div id="messageContainer"></div><br>
                                <button class="close-button buttonTable" onclick="submitForm()">ADD RECIPE</button>
                                <a href="ShowRecipeList" class="btn btn-dark">Cancel</a>
                            </div>

                            <script>
                                function submitForm() {
                                    document.getElementById("myFormAdd").submit();
                                }

                                function saveIngredient() {
                                    var ingredientId = document.getElementById("ingredientDropdown").value;
                                    var ingredientName = document.getElementById("ingredientDropdown").options[document.getElementById("ingredientDropdown").selectedIndex].text;
                                    var quantity = document.getElementById("quantityInput").value;
                                    var ingredientListContainer = document.getElementById("ingredientList");

                                    if (ingredientId === "0") {
                                        alert("Please select an ingredient.");
                                        return;
                                    }

                                    if (quantity === "") {
                                        alert("Please enter a quantity.");
                                        return;
                                    }

                                    var ingredientItem = document.createElement("div");
                                    ingredientItem.innerHTML = ingredientName + " : " + quantity;
                                    ingredientListContainer.appendChild(ingredientItem);

                                    // Create hidden input fields for ingredient IDs and quantities
                                    var ingredientIdInput = document.createElement("input");
                                    ingredientIdInput.setAttribute("type", "hidden");
                                    ingredientIdInput.setAttribute("name", "ingredient_id");
                                    ingredientIdInput.setAttribute("value", ingredientId);
                                    document.getElementById("myFormAdd").appendChild(ingredientIdInput);

                                    var quantityInput = document.createElement("input");
                                    quantityInput.setAttribute("type", "hidden");
                                    quantityInput.setAttribute("name", "quantityInput");
                                    quantityInput.setAttribute("value", quantity);
                                    document.getElementById("myFormAdd").appendChild(quantityInput);

                                    document.getElementById("ingredientDropdown").value = "0";
                                    document.getElementById("quantityInput").value = "";
                                }

                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <style>
            .dropdown {
                position: relative;
                left: 0px;
                top: 0px;
            }
        </style>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

