package za.co.mie.bakeryService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import za.co.mie.bakeryDao.BakeryDaoOrderImpl;
import za.co.mie.model.Order;
import za.co.mie.model.OrderItem;

public class BakeryServiceOrderImpl implements BakeryServiceOrderInterface {

    private BakeryServiceOrderInterface bakeryDaoOrderInterface;
    private BakeryDaoOrderImpl bdoi;

    public BakeryServiceOrderImpl(BakeryDaoOrderImpl bdoi) {
        this.bdoi = bdoi;
    }

    @Override
    public List<Order> getAllOrdersForUser(String userId) {
        return userId == null ? null : bdoi.getAllOrdersForUser(userId);
    }

    public List<OrderItem> getAllLineItems(int order_id) {
        return order_id < 0 ? null : bdoi.getAllLineItems(order_id);
    }

    @Override
    public List<Order> getAllOutstandingOrdersForUser(int userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList();
        return list == null ? null : bdoi.getAllOrders();
    }

    @Override
    public List<Order> getAllOrders(LocalDate fromDate, LocalDate toDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getAllProceesedOrders() {
        List<Order> list = new ArrayList();
        return list == null ? null : bdoi.getAllProceesedOrders();
    }

    @Override
    public List<Order> getAllOutstandingOrders() {
        List<Order> list = new ArrayList();
        return list == null ? null : bdoi.getAllOutstandingOrders();
    }

    @Override
    public Order getSingleOrder(int id) {
        return id < 0 ? null : bdoi.getSingleOrder(id);
    }

    @Override
    public boolean placeOrder(Order order) {
        return order == null ? false : bdoi.placeOrder(order);
    }

    @Override
    public boolean removeOrder(int orderId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean disableOrder(int order_id) {
        return order_id < 0 ? false : bdoi.disableOrder(order_id);
    }

}
