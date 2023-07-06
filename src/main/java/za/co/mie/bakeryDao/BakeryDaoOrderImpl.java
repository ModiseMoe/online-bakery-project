package za.co.mie.bakeryDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import za.co.mie.model.Ingridient;
import za.co.mie.model.Order;
import za.co.mie.model.OrderItem;
import za.co.mie.model.Product;

public class BakeryDaoOrderImpl implements BakeryDaoOrderInterface {

    private Connection con = null;
    private PreparedStatement pst;
    private PreparedStatement ps;
    private ResultSet rs = null;

    public BakeryDaoOrderImpl(Connection con) {
        this.con = con;
    }

    @Override
    public List<Order> getAllOrdersForUser(String userId) {
        List<Order> orders = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM orders WHERE user_emailAdd=? AND status=true ");
                ps.setString(1, userId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    orders.add(new Order(rs.getDate("order_date"), rs.getString("user_emailAdd"), rs.getInt("order_id"), rs.getBoolean("payment_status"), rs.getBoolean("status")));
                }
            } catch (SQLException ex) {
                System.out.println("Error!!: " + ex.getMessage());
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }
        return orders;
    }

    public List<OrderItem> getAllLineItems(int order_id) {
        List<OrderItem> orderItem = new ArrayList<>();
        if (con != null) {
            try {
                pst = con.prepareStatement("SELECT * FROM order_line_item WHERE order_id = ?");
                pst.setInt(1, order_id);
                rs = pst.executeQuery();
                while (rs.next()) {
                    orderItem.add(new OrderItem(rs.getInt("product_id"), rs.getInt("quantity"), rs.getDouble("unitPrice"), rs.getString("product_title"), rs.getInt("order_id")));
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
        return orderItem;
    }

    @Override
    public List<Order> getAllOutstandingOrdersForUser(int userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> allOrders = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM orders ");
                rs = ps.executeQuery();
                while (rs.next()) {
                    allOrders.add(new Order(rs.getDate("order_date"), rs.getString("user_emailAdd"), rs.getInt("order_id"), rs.getBoolean("payment_status"), rs.getBoolean("status")));
                }
            } catch (SQLException ex) {
                System.out.println("Error!!: " + ex.getMessage());
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }
        return allOrders;
    }

    @Override
    public List<Order> getAllOrders(LocalDate fromDate, LocalDate toDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getAllProceesedOrders() {
        List<Order> allProcessedOrders = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM orders WHERE payment_status=?");
                ps.setBoolean(1, true);
                rs = ps.executeQuery();
                while (rs.next()) {
                    allProcessedOrders.add(new Order(rs.getDate("order_date"), rs.getString("user_emailAdd"), rs.getInt("order_id"), rs.getBoolean("payment_status"), rs.getBoolean("status")));
                }
            } catch (SQLException ex) {
                System.out.println("Error!!: " + ex.getMessage());
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }
        return allProcessedOrders;
    }

    @Override
    public List<Order> getAllOutstandingOrders() {
        List<Order> allOutstandingOrders = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM orders WHERE payment_status=? AND status=?");
                ps.setBoolean(1, false);
                ps.setBoolean(2, true);
                rs = ps.executeQuery();
                while (rs.next()) {
                    allOutstandingOrders.add(new Order(rs.getDate("order_date"), rs.getString("user_emailAdd"), rs.getInt("order_id"), rs.getBoolean("payment_status"), rs.getBoolean("status")));
                }
            } catch (SQLException ex) {
                System.out.println("Error!!: " + ex.getMessage());
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }
        return allOutstandingOrders;
    }

    @Override
    public Order getSingleOrder(int id) {

        Order order = new Order();
        if (con != null) {
            try {
                ps = con.prepareStatement(" SELECT * FROM orders WHERE order_id= ?");
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    order = new Order(rs.getDate("order_date"), rs.getString("user_emailAdd"), rs.getInt("order_id"), rs.getBoolean("payment_status"), rs.getBoolean("status"));
                }
            } catch (SQLException ex) {
                System.out.println("Error!!: " + ex.getMessage());
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }
        return order;
    }

    @Override
    public boolean disableOrder(int order_id) {
        boolean retVal = false;
        if (con != null && order_id > 0) {
            try {
                pst = con.prepareStatement("UPDATE orders SET status=? WHERE order_id=?");
                pst.setBoolean(1, false);
                pst.setInt(2, order_id);
                retVal = pst.executeUpdate() > 0;
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
        return retVal;

    }

    @Override
    public boolean placeOrder(Order order) {

        boolean retVal = false;

        try {
            // Insert the order into the database
            String insertOrderQuery = "INSERT INTO orders (user_emailAdd, order_date) VALUES (?, ?)";
            pst = con.prepareStatement(insertOrderQuery, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, order.getUserId());
            pst.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                // Retrieve the generated order ID
                ResultSet generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);

                    // Insert the order items into the database
                    pst = con.prepareStatement("INSERT INTO order_line_item (order_id, product_id,quantity ,unitPrice , product_title) VALUES (?, ?, ?, ?, ?)");
                    for (OrderItem orderItem : order.getOrderItems()) {
                        pst.setInt(1, orderId);
                        pst.setInt(2, orderItem.getProduct_id());
                        pst.setInt(3, orderItem.getProductQuantity());
                        pst.setDouble(4, orderItem.getUnitPrice());
                        pst.setString(5, orderItem.getProduct_title());
                        pst.addBatch();
                    }

                    int[] batchResults = pst.executeBatch();
                    retVal = Arrays.stream(batchResults).allMatch(result -> result != PreparedStatement.EXECUTE_FAILED && result != 0);

                }
            }
        } catch (SQLException e) {
            System.out.println("Error placing order: " + e.getMessage());
        } finally {
            // Close the statement
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing statement: " + e.getMessage());
            }
        }

        return retVal;
    }
//   public boolean placeOrder(Order order) {
//        boolean retVal = false;
//        PreparedStatement pst = null;
//
//        try {
//            pst = con.prepareStatement("INSERT INTO orders (order_date, user_email_Add,product_quantity, product_id) VALUES (?, ?, ?, ?)");
//
//            pst.setDate(1, new java.sql.Date(order.getOrderDate().getTime()));
//            pst.setString(2, order.getUserId());
//           
//
//            // Iterate over the order items and insert them into the database
//            for (OrderItem orderItem : order.getOrderItems()) {
//                pst.setInt(3, orderItem.getProductQuantity());
//                pst.setInt(4, orderItem.getProduct_id());
//                int rowsAffected = pst.executeUpdate();
//                if (rowsAffected > 0) {
//                    retVal = true;
//                } else {
//                    retVal = false;
//                    break;  // Stop inserting if any order item fails to be inserted
//                }
//            }
//        } catch (SQLException ex) {
//            System.out.println("Error placing order: " + ex.getMessage());
//        } finally {
//            if (pst != null) {
//                try {
//                    pst.close();
//                } catch (SQLException ex) {
//                    System.out.println("Error closing PreparedStatement: " + ex.getMessage());
//                }
//            }
//        }
//
//        return retVal;
//    }

    @Override
    public boolean removeOrder(int orderId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public boolean subIngredients(Order order) {
//        boolean retVal = false;
//        if (con != null && order != null) {
//            try {
//                List<Product> products = getProductsInAnOrder(order);
//                int mQty = 0;
//                for (Product pr : products) {
//                    int recipeId = getRecipeId(pr.getId());
//                    List<Ingridient> ingrList = recipeIngredientsList(recipeId);
//                    for (Ingridient ingr : ingrList) {
//                        pst = con.prepareStatement("UPDATE ingredients SET quantityOnHand=? WHERE ingredient_id=?");
//                        ps = con.prepareStatement("SELECT quantityOnHand FROM ingredients WHERE ingredient_id=?");
//                        ps.setInt(1, ingr.getIngredientId());
//                        rs = ps.executeQuery();
//                        while (rs.next()) {
//                            mQty = rs.getInt("quantityOnHand");
//                        }
//                        pst.setInt(1, mQty - (ingr.getMinimumStockQuantity() * pr.getQuantity()));
//                        pst.setInt(2, ingr.getIngredientId());
//                        retVal = pst.executeUpdate() > 0;
//                    }
//                }
//
//            } catch (SQLException ex) {
//                System.out.println("Error!!: " + ex.getMessage());
//            } finally {
//                if (pst != null) {
//                    try {
//                        pst.close();
//                    } catch (SQLException ex) {
//                        System.out.println("Could not close: " + ex.getMessage());
//                    }
//                }
//            }
//        }
//        return retVal;
//    }
//
// 
//    public List<Product> getProductsInAnOrder(Order order) {
//        List<Product> productsInAnOrder = new ArrayList<>();
//        if (con != null) {
//            try {
//                ps = con.prepareStatement("SELECT * FROM order_line_item WHERE order_id=?");
//                ps.setInt(1, order.getOrder_id());
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    productsInAnOrder.add(new Product(rs.getString("product_title"), rs.getInt("product_id"), rs.getInt("quantity")));
//                }
//            } catch (SQLException ex) {
//                System.out.println("Error!!: " + ex.getMessage());
//            } finally {
//                if (ps != null) {
//                    try {
//                        ps.close();
//                    } catch (SQLException ex) {
//                        System.out.println("Could not close: " + ex.getMessage());
//                    }
//                }
//            }
//        }
//        return productsInAnOrder;
//    }
//
//  
//    public int getRecipeId(int productId) {
//        int recipeId = 0;
//        if (con != null) {
//            try {
//                ps = con.prepareStatement(" SELECT recipe_id FROM products WHERE product_id=?");
//                ps.setInt(1, productId);
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    recipeId = rs.getInt("recipe_id");
//                }
//            } catch (SQLException ex) {
//                System.out.println("Error!!: " + ex.getMessage());
//            } finally {
//                if (ps != null) {
//                    try {
//                        ps.close();
//                    } catch (SQLException ex) {
//                        System.out.println("Could not close: " + ex.getMessage());
//                    }
//                }
//            }
//        }
//        return recipeId;
//
//    }
//    
//
//    public List<Ingridient> recipeIngredientsList(int recipeId) {
//        List<Ingridient> ingresInAnOrder = new ArrayList<>();
//        if (con != null) {
//            try {
//                ps = con.prepareStatement("SELECT ingredient_id,quantity FROM recipe_ingredient WHERE recipe_id=?");
//                ps.setInt(1, recipeId);
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    ingresInAnOrder.add(new Ingridient(rs.getInt("quantity"), rs.getInt("ingredient_id")));
//                }
//            } catch (SQLException ex) {
//                System.out.println("Error!!: " + ex.getMessage());
//            } finally {
//                if (ps != null) {
//                    try {
//                        ps.close();
//                    } catch (SQLException ex) {
//                        System.out.println("Could not close: " + ex.getMessage());
//                    }
//                }
//            }
//        }
//        return ingresInAnOrder;
//    }
//}
    
}
