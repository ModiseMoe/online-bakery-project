/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.co.mie.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;
import za.co.mie.bakeryDao.BakeryDaoUsersImp;
import za.co.mie.bakeryService.BakeryServiceUsersImp;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.User;

@WebServlet(name = "UserLogin", urlPatterns = {"/UserLogin"})
public class UserLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryServiceUsersImp bsui = new BakeryServiceUsersImp(new BakeryDaoUsersImp(con));
        
        try(PrintWriter out = response.getWriter()){
            String email = request.getParameter("emailAddr");
            String password = request.getParameter("password");
            //String hashPwd = BCrypt.hashpw(password, BCrypt.gensalt());
          
            User user =bsui.login(new User(password,email));
            RequestDispatcher rd = null;
            if(user != null && BCrypt.checkpw(password, user.getPassword()) && user.getUserRole().equals("User") && user.isStatus()){
                out.print("User Succesfully logged in");
                request.getSession().setAttribute("auth", user);
                 rd = request.getRequestDispatcher("indexPageList");
 
                rd.forward(request, response);
            }else if(user != null && BCrypt.checkpw(password, user.getPassword()) && user.getUserRole().equals("Admin") && user.isStatus()){
                out.print("logging in");
                request.getSession().setAttribute("auth", user);
               rd = request.getRequestDispatcher("AdminLandingPage.jsp");
               rd.forward(request, response);
            }else{
                
                request.setAttribute("loginFailedMessage", "Invalid email or password. Please try again.");
                response.sendRedirect("login.jsp");
            }
        }catch(IOException io){
            System.out.println("error" + io.getMessage());
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
           
        }
    }


