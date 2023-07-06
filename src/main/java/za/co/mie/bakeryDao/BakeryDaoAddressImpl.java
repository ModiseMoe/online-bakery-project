
package za.co.mie.bakeryDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import za.co.mie.model.Address;
import za.co.mie.model.PaymentDetails;
import za.co.mie.model.User;

public class BakeryDaoAddressImpl implements BakeryDaoAddressInterface {
      private Connection con = null;
    private PreparedStatement pst;
    private PreparedStatement ps;
    private ResultSet rs;

    public BakeryDaoAddressImpl(Connection con) {
        this.con = con;
    }
    
    
    
    @Override
    public boolean addAddressToUser(String userId, int addressId) {
        boolean retVal = false;
        if (con != null) {
            try {
                con.setAutoCommit(false);
                pst = con.prepareStatement("UPDATE user SET address_id=? WHERE user_emailAdd=?");
                pst.setInt(1, addressId);
                pst.setString(2, userId);
                if (pst.executeUpdate() > 0) {
                    ps = con.prepareStatement("UPDATE address SET user_id=? WHERE address_id=?");
                    ps.setString(1, userId);
                    ps.setInt(2, addressId);
                    if (ps.executeUpdate() > 0) {
                        con.commit();
                        retVal=true;
                    } else {
                        con.rollback();
                    }
                }
            } catch (SQLException ex) {
                try {
                    con.rollback();
                } catch (SQLException ex1) {
                }
                System.out.println("Error!!: " + ex.getMessage());
            } finally {
                if (pst != null) {
                    try {
                        pst.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    } finally {
                        pst = null;
                    }
                }
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    } finally {
                        ps = null;
                    }
                }
                if (con != null) {
                    try {
                        con.setAutoCommit(true);
                    } catch (SQLException ex) {
                    }
                }
            }
        }
        return retVal;
    }
    
    
     @Override
    public boolean addAddress(Address address) {
        boolean retVal = false;
        if (con != null && address != null) {
            try {
                pst = con.prepareStatement("INSERT INTO address(address_id,address_details,city,postal_code,user_id) VALUE(NULL,?,?,?,NULL)");
                pst.setString(1, address.getAddress_details());
                pst.setString(2, address.getCity());
                pst.setString(3, address.getPostal_code());
                retVal = pst.executeUpdate() > 0;
            } catch (SQLException ex) {
                System.out.println("Error!!: " + ex.getMessage());
            } finally {
                if (pst != null) {
                    try {
                        pst.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    } finally {
                        pst = null;
                    }
                }
            }
        }
        return retVal;
    }
    
    
    @Override
    public boolean addAddressToExistingUser(Address address, String userId , int order_id) {
        boolean retVal = false;
        if (con != null && address != null) {
            try {
                pst = con.prepareStatement("INSERT INTO address(address_id,address_details,city,postal_code,user_id , order_id,country) VALUE(NULL,?,?,?,?,?,?)");
                pst.setString(1, address.getAddress_details());
                pst.setString(2, address.getCity());
                pst.setString(3, address.getPostal_code());
                pst.setString(4, userId);
                pst.setInt(5,order_id );
                pst.setString(6, address.getCountry());
                retVal = pst.executeUpdate() > 0;
            } catch (SQLException ex) {
                System.out.println("Error!!: " + ex.getMessage());
            } finally {
                if (pst != null) {
                    try {
                        pst.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    } finally {
                        pst = null;
                    }
                }
            }
        }
        return retVal;
    }
 @Override
    public boolean addUserToExistingAddress(User user, int addressId) {
        boolean retVal = false;
        if (con != null && user != null) {
            try {
                pst = con.prepareStatement("INSERT INTO user(user_name,user_pwd,user_surname,user_idNo,user_role,user_mobileNum,user_emailAdd, address_id) values(?,?,?,?,?,?,?,?)");

                pst.setString(1, user.getName());
                pst.setString(2, user.getPassword());
                pst.setString(3, user.getSurname());
                pst.setString(4, user.getId_number());
                pst.setString(5, user.getUserRole());
                pst.setString(6, user.getMobileNumber());
                pst.setString(7, user.getEmailAddr());
                pst.setInt(8, addressId);
                retVal = pst.executeUpdate() > 0;
            } catch (SQLException ex) {
                System.out.println("Error!!: " + ex.getMessage());
            } finally {
                if (pst != null) {
                    try {
                        pst.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    } finally {
                        pst = null;
                    }
                }
            }
        }
        return retVal;
    }
        @Override
      public List<Address> getAddressList(String user_id){
        List<Address> addressList = new ArrayList<>();
         if (con != null) {
            try {
                pst = con.prepareStatement("SELECT * FROM address WHERE user_id= ?");
                pst.setString(1, user_id);
                rs = pst.executeQuery();
                while (rs.next()) {
                    addressList.add(new Address(rs.getString("address_details") , rs.getString("city") , rs.getString("postal_code") ,rs.getInt("address_id"), rs.getString("country")));
                }
            } catch (SQLException ex) {
                System.out.println("Error!!: " + ex.getMessage());
            } finally {
                if (pst != null) {
                    try {
                        pst.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }
        return addressList;
    }
      
      @Override
       public boolean addPaymentDetails(PaymentDetails paymentDetails) {
        boolean retVal = false;
        if (con != null && paymentDetails != null) {
            try {
                pst = con.prepareStatement("INSERT INTO payment_details(cardHolderName , cardNumber , expire , cvv) VALUE(?,?,?,?)");
                pst.setString(1, paymentDetails.getCardholder_name());
                pst.setString(2, paymentDetails.getCardNumber());
                pst.setString(3, paymentDetails.getExpireDate());
                pst.setString(4, paymentDetails.getExpireDate());
                pst.setString(3, paymentDetails.getCvv());
                retVal = pst.executeUpdate() > 0;
            } catch (SQLException ex) {
                System.out.println("Error!!: " + ex.getMessage());
            } finally {
                if (pst != null) {
                    try {
                        pst.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    } finally {
                        pst = null;
                    }
                }
            }
        }
        return retVal;
    }
       
      @Override
    public boolean PaymentStubForOrder(int order_id){
        boolean retVal = false;
        if(con != null && order_id >0){
            Random random = new Random();
            int randomNumber = random.nextInt(10)+1;
            boolean paymentStatus = (randomNumber < 5)? false : true;
            
            retVal = updatePaymentStatus(order_id ,paymentStatus);
        }
        return retVal;
    }
    
    public boolean updatePaymentStatus(int order_id , boolean paymentStatus){
        boolean retVal = false;
        if(con!= null && order_id >0){
           try{
                pst = con.prepareStatement("UPDATE orders SET payment_status =? WHERE order_id= ?");
                pst.setBoolean(1, paymentStatus);
                pst.setInt(2, order_id);
                retVal = pst.executeUpdate() > 0;
           }catch(SQLException ex){
               System.out.println("could not close" + ex.getMessage());
           }finally{
               pst =null;
           }
        }
      return retVal;  
    } 
   
}


    

