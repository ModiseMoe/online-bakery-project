
package za.co.mie.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.mie.bakeryDao.BakeryDaoAddressImpl;
import za.co.mie.bakeryDao.BakeryDaoCategoryImpl;
import za.co.mie.bakeryDao.BakeryDaoIngredientsImpl;
import za.co.mie.bakeryDao.BakeryDaoProductImpl;
import za.co.mie.bakeryDao.BakeryDaoUsersImp;
import za.co.mie.bakeryService.BakeryServiceCategoryImpl;
import za.co.mie.bakeryService.BakeryServiceIngredientsImpl;
import za.co.mie.bakeryService.BakeryServiceProductImpl;
import za.co.mie.bakeryService.BakeryServiceUsersImp;
import za.co.mie.db.listener.DBManager;
import za.co.mie.model.Product;
//import za.co.mie.model.Cart;

//***************ONLY FOR TESTING !!!*******************************
@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        //************************
        DBManager dbm = (DBManager) request.getServletContext().getAttribute("dbman");
        Connection con = dbm.getConnection();
        BakeryDaoUsersImp bdi = new BakeryDaoUsersImp(con);
        BakeryDaoAddressImpl bdai = new  BakeryDaoAddressImpl(con);
        BakeryDaoCategoryImpl bdci = new BakeryDaoCategoryImpl(con);
        BakeryDaoProductImpl bdpi = new BakeryDaoProductImpl(con);
        
        BakeryServiceCategoryImpl bsci = new BakeryServiceCategoryImpl(new BakeryDaoCategoryImpl(con));
        BakeryServiceProductImpl bspi = new BakeryServiceProductImpl(new BakeryDaoProductImpl(con));
         BakeryServiceUsersImp bsui = new BakeryServiceUsersImp(new BakeryDaoUsersImp(con));
          BakeryServiceIngredientsImpl bsii = new BakeryServiceIngredientsImpl(new BakeryDaoIngredientsImpl(con));
       // User user =bdi.login( new User("fripp" ,"stuart@gmail.com" ));
     //  boolean val = bsui.disableUser("eish@gmail.com1");
       
       
     
       
       String name = bsii.getUnitForIngredient(17);
        System.out.println(name);
       
       
//       
//       List<Product> prod = null;
//       prod = bspi.getProductsbyCategory(19);
       
//       if(prod != null){
//          for(Product p : prod){
//              System.out.println(p);
//          }
//       }else{
//           System.out.println("failed");
//       }
//        
        //Product product = bdpi.getSingleProduct("Cheese cakes");
        
//        String name = null;
//      name =  bdpi.getCategoryForProduct(11);
//        
//      
//        
//        if(name!= null){
//            System.out.println(name);
//        }else{
//            System.out.println("failed");
 //       }
        
        
        
        
        
//        ArrayList<Cart> cartList = new ArrayList();
//        Cart c = new Cart();
//        c.getQuantity();
//        c.getProduct_title();
//        c.getDescription();
//        c.getProductPrice();
//        c.getProductWarnigs();
//        cartList.add(c);
//        
//        List<Cart> cartItems = null;
//        
//        cartItems = bdpi.getCartProducts(cartList);
//        
//        if(cartItems != null){
//            System.out.println(cartItems);
//        }else{
//            System.out.println(" no items");
//        }
//        
        
        
//        boolean val =false;
//       val = bspi.activateProduct("Chocolate Brownies");

//         if(product!=null){
//          System.out.println(product);
//       }else{
//          System.out.println(" not valid");
//     }
        
//        List<Product> myList = null;
//        myList = bspi.getAllProducts();
//        if(myList!= null){
//        System.out.println("********************");
//      for(Product list : myList){
//          System.out.println(list); 
//       }
//    
//    }else{
//          System.out.println("dose not exist");
//      }
//       
//       
       
//      
//         val = bspi.addNewProduct(new Product("Vanilla brownies", " vanilla brownies with added choc chips " ,  "Contains nuts and chololate additives",  20.00));
//       if(val!= false){
//          System.out.println(val);
//       }else{
//          System.out.println(" not valid");
//     }
      
       
       
       
       
       
       
       
//           boolean category = false;
//          category  = bdci.disableCategory("nyikoz");
//
//        boolean update = false;
//       update =  bdci.updateCategory(new Category("baked-space-cookies"), "Tshepang");
//       
//       if(update != false){
//           System.out.println(update);
//       }else{
//           System.out.println("update failed");
//       }
//        

//       
//        if(category != false){
//            System.out.println(category);
//        }else{
//           System.out.println(" not valid");
//        }
//       
//      List<Category> catList = null;
//   catList = bsci.getAllCategories();
//     
               
     
       // Category cat = bdci.getSingleCategory("Cookies");
      // boolean val = bdci.updateCategory(new Category("Cakes"), "superSpaceCookies");
        
       // Address addr = new Address("6 jacobus" , "kemdo" , "1234");
       //bdi.updateUser(user);
      
//        
//        
      
     
       // dao.add(con);
        //************************

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TestServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
