
package za.co.mie.bakeryDao;

import java.util.List;
import za.co.mie.model.Address;
import za.co.mie.model.PaymentDetails;
import za.co.mie.model.User;


public interface BakeryDaoAddressInterface {
      public boolean addAddress(Address address);

    boolean addAddressToUser(String userId, int addressId);

    boolean addUserToExistingAddress(User user, int addressId);

    public boolean addAddressToExistingUser(Address address, String userId , int order_id);
     public List<Address> getAddressList(String user_id);
     public boolean addPaymentDetails(PaymentDetails paymentDetails);
      public boolean PaymentStubForOrder(int order_id);
      
    
}
