package za.co.mie.bakeryDao;

import java.time.LocalDate;
import java.util.List;
import za.co.mie.model.Order;
import za.co.mie.model.OrderItem;

public interface BakeryDaoOrderInterface {

    public List<Order> getAllOrdersForUser(String userId);

    public List<OrderItem> getAllLineItems(int order_id);

    public List<Order> getAllOutstandingOrdersForUser(int userId);

    public List<Order> getAllOrders();

    public List<Order> getAllOrders(LocalDate fromDate, LocalDate toDate);

    public List<Order> getAllProceesedOrders();

    public List<Order> getAllOutstandingOrders();

    public Order getSingleOrder(int id);

    public boolean placeOrder(Order order);

    public boolean removeOrder(int orderId);

    public boolean disableOrder(int order_id);
}
