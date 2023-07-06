package za.co.mie.controller;

import za.co.mie.bakeryDao.BakeryDaoAddressImpl;
import za.co.mie.bakeryDao.BakeryDaoOrderImpl;
import za.co.mie.bakeryService.BakeryServiceAddressImpl;
import za.co.mie.bakeryService.BakeryServiceOrderImpl;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.Address;
import za.co.mie.model.Order;
import za.co.mie.model.PaymentDetails;
import za.co.mie.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import za.co.mie.model.Email;

@WebServlet(name = "AddressAndPurchase", urlPatterns = {"/AddressAndPurchase"})
public class AddressAndPurchase extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        boolean retVal = false;
        boolean retVal1 = false;
        boolean retVal2 = false;
        Order singleOrder = null;
        User auth = (User) request.getSession().getAttribute("auth");
        BakeryServiceAddressImpl bsai = new BakeryServiceAddressImpl(new BakeryDaoAddressImpl(con));
         BakeryServiceOrderImpl bsoi = new BakeryServiceOrderImpl(new BakeryDaoOrderImpl(con));
         Email mail = new Email();

        try (PrintWriter out = response.getWriter()) {
            //*********************Address details**************************************************
            int order_id = Integer.parseInt(request.getParameter("order_id"));
            String address_details = request.getParameter("address_details");
            String city = request.getParameter("city");
            String postal_code = request.getParameter("postal_code");
            String country = request.getParameter("country");
            
            //********************Payment details************************************
            String cardHolder_name = request.getParameter("cardHolder_name");
            String cardNumber = request.getParameter("cardNumber");
            String expire = request.getParameter("expire");
            String cvv = request.getParameter("cvv");
            
            retVal = bsai.addAddressToExistingUser(new Address(address_details, city, postal_code, country), auth.getEmailAddr(), order_id);
            retVal1 = bsai.addPaymentDetails(new PaymentDetails(cardHolder_name, cardNumber ,expire , cvv ));
            retVal2 = bsai.PaymentStubForOrder(order_id);
            singleOrder = bsoi.getSingleOrder(order_id);
            
            
            
            if (retVal && retVal1  && retVal2  && singleOrder != null) {
                request.setAttribute("one_order", singleOrder);
                
                if(singleOrder.isPayment_status()){
                    try {
                        mail.sendReceiptEmail(auth.getName(), order_id, auth.getEmailAddr());
                    } catch (MessagingException ex) {
                        System.out.println("Error"+ ex.getMessage());
                    }
                    
                }
                else{
                    try {
                        mail.sendfailedPurchaseEmail(auth.getName(), order_id,auth.getEmailAddr());
                    } catch (MessagingException ex) {
                        System.out.println("Error" + ex.getMessage());
                    }
                }
               request.getRequestDispatcher("paymentNotifier.jsp").forward(request, response);
            } else {
                out.print("failed");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // Helper method to get the current date and time
    private String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "Date and Time: " + dateFormat.format(new Date());
    }

}
