package za.co.mie.bakeryService;

import java.util.List;
import za.co.mie.bakeryDao.BakeryDaoAddressImpl;
import za.co.mie.model.Address;
import za.co.mie.model.PaymentDetails;
import za.co.mie.model.User;

public class BakeryServiceAddressImpl implements BakeryServiceAddressInterface {

    private BakeryDaoAddressImpl bdai;

    public BakeryServiceAddressImpl(BakeryDaoAddressImpl bdai) {
        this.bdai = bdai;
    }

    @Override
    public boolean addAddress(Address address) {
        return address == null ? false : bdai.addAddress(address);
    }

    @Override
    public boolean addAddressToUser(String userId, int addressId) {
        return userId == null ? false : bdai.addAddressToUser(userId, addressId)
                && addressId > 0 ? bdai.addAddressToUser(userId, addressId) : null;
    }

    @Override
    public boolean addUserToExistingAddress(User user, int addressId) {
        return user == null ? false : bdai.addUserToExistingAddress(user, addressId)
                && addressId > 0 ? bdai.addUserToExistingAddress(user, addressId) : null;
    }

    @Override
    public boolean addAddressToExistingUser(Address address, String userId , int order_id) {
        return address == null && userId == null  && order_id < 0  ? false : bdai.addAddressToExistingUser(address, userId, order_id);
                
    }

    @Override
    public List<Address> getAddressList(String user_id) {
       return user_id == null ? null : bdai.getAddressList(user_id);
    }

    @Override
    public boolean addPaymentDetails(PaymentDetails paymentDetails) {
       return paymentDetails == null ? false : bdai.addPaymentDetails(paymentDetails);
    }

    @Override
    public boolean PaymentStubForOrder(int order_id) {
        return  order_id < 0 ? false : bdai.PaymentStubForOrder(order_id);
    }
}
