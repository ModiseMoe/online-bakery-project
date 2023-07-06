package za.co.mie.bakeryDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.co.mie.model.CartLineItem;
import za.co.mie.model.Product;

public class BakeryDaoProductImpl implements BakeryDaoProductInterface {

    private Connection con = null;
    private PreparedStatement pst;
    private PreparedStatement ps;
    private ResultSet rs = null;

    public BakeryDaoProductImpl(Connection con) {
        this.con = con;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM products");
                
                rs = ps.executeQuery();
                while (rs.next()) {
                    allProducts.add(new Product(rs.getString("product_title"), rs.getString("product_desc"), rs.getString("product_warnings"), rs.getDouble("product_price"), rs.getBoolean("status"), rs.getInt("product_id"), rs.getString("image")));
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
        return allProducts;
    }
    
    @Override
    public List<Product> performSearch( String product_title) {
        List<Product> searchResults = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM products WHERE product_title LIKE ?");
                ps.setString(1, "%"+product_title+"%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    searchResults.add(new Product(rs.getString("product_title"), rs.getString("product_desc"), rs.getString("product_warnings"), rs.getDouble("product_price"), rs.getBoolean("status"), rs.getInt("product_id"), rs.getString("image")));
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
        return searchResults;
    }
    
   
    
    @Override
    public List<Product> getAllProductsByStatus() {
        List<Product> allProducts = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM products WHERE status=true");
                rs = ps.executeQuery();
                while (rs.next()) {
                    allProducts.add(new Product(rs.getString("product_title"), rs.getString("product_desc"), rs.getString("product_warnings"), rs.getDouble("product_price"), rs.getBoolean("status"), rs.getInt("product_id"), rs.getString("image")));
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
        return allProducts;
    }
    

    @Override
    public List<Product> getAllProductsFromCategory(String category_name) {
        List<Product> allProducts = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM products WHERE cat_id =?");
                ps.setString(1, category_name);
                rs = ps.executeQuery();
                while (rs.next()) {
                    allProducts.add(new Product(rs.getString("product_title"), rs.getString("product_desc"), rs.getString("product_warnings"), rs.getDouble("product_price"), rs.getBoolean("status"), rs.getInt("product_id"), rs.getString("image")));
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
        return allProducts;
    }

    @Override
    public Product getSingleProduct(int product_id) {
        Product product = new Product();
        if (con != null) {
            try {
                ps = con.prepareStatement(" SELECT * FROM products WHERE product_id= ?");
                ps.setInt(1, product_id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    product = new Product(rs.getString("product_title"), rs.getString("product_desc"), rs.getString("product_warnings"), rs.getDouble("product_price"), rs.getBoolean("status"), rs.getInt("product_id"), rs.getString("image"));
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
        return product;
    }
     public List<Product> getProductsbyCategory(int cat_id){
          List<Product> ProductsByCat = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM products WHERE cat_id=? AND status=true");
                ps.setInt(1, cat_id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ProductsByCat.add(new Product(rs.getString("product_title"), rs.getString("product_desc"), rs.getString("product_warnings"), rs.getDouble("product_price"), rs.getBoolean("status"), rs.getInt("product_id"), rs.getString("image")));
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
        return ProductsByCat;
     }

    public String getCategoryForProduct(int product_id) {
        String cat_name = null;
        if (con != null) {
            try {
                ps = con.prepareStatement(" SELECT category.category_name FROM products JOIN category ON products.cat_id = category.cat_id WHERE products.product_id = ?");

                ps.setInt(1, product_id);
                rs = ps.executeQuery();
                if (rs.next()) 
                {
                    cat_name = rs.getString("category_name");
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
        return cat_name;
    }
    
   

    @Override
    public boolean addNewProduct(Product product , int recipe_id) {
        boolean retVal = false;
        if (con != null && product != null) {
            try {
                pst = con.prepareStatement("INSERT INTO products(product_title,product_desc,product_warnings,recipe_id,product_price,image,discount_id,cat_id) values(?,?,?,?,?,?,null,null)");

                pst.setString(1, product.getProduct_title());
                pst.setString(2, product.getDescription());
                pst.setString(3, product.getProductWarnigs());
                pst.setInt(4, recipe_id);
                pst.setDouble(5, product.getProductPrice());
                pst.setString(6, product.getImage());

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
    public boolean updateProduct(int product_id, Product product1) {
        boolean retVal = false;
        if (con != null && product_id > 0) {
            try {
                pst = con.prepareStatement("UPDATE products SET product_title=?,product_desc=?,product_warnings=?, product_price=? , image=? WHERE product_id=?");
                pst.setString(1, product1.getProduct_title());
                pst.setString(2, product1.getDescription());
                pst.setString(3, product1.getProductWarnigs());
                pst.setDouble(4, product1.getProductPrice());
                pst.setString(5, product1.getImage());
                pst.setInt(6, product_id);
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
    public boolean deleteProduct(int productId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addProductToCategory(int productId, int cat_id) {
        boolean retVal = false;
        if (con != null) {
            try {
                pst = con.prepareStatement("UPDATE products SET cat_id=? WHERE product_id=?");
                pst.setInt(1, cat_id);
                pst.setInt(2, productId);
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
//               

    }

    @Override
    public int getProductId(String productName) {
        int productId = 0;
        if (con != null) {
            try {
                ps = con.prepareStatement(" SELECT product_id FROM products WHERE product_title= ?");
                ps.setString(1, productName);
                rs = ps.executeQuery();
                while (rs.next()) {
                    productId = rs.getInt("product_id");
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
        return productId;
    }

    @Override
    public boolean activateProduct(int product_id) {

        boolean retVal = false;
        if (con != null && product_id > 0) {
            try {
                pst = con.prepareStatement("UPDATE products SET status=? WHERE product_id=?");
                pst.setBoolean(1, true);
                pst.setInt(2, product_id);
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
    public boolean disableProduct(int product_id) {
        boolean retVal = false;
        if (con != null && product_id > 0) {
            try {
                pst = con.prepareStatement("UPDATE products SET status=? WHERE product_id=?");
                pst.setBoolean(1, false);
                pst.setInt(2, product_id);
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
    public List<CartLineItem> getCartProducts(List<CartLineItem> cart) {
        try {
            for (CartLineItem item : cart) {
                pst = con.prepareStatement("SELECT * from products WHERE product_id=?");
                pst.setInt(1, item.getProduct().getId());
                rs = pst.executeQuery();
                while (rs.next()) {
                    item.getProduct().setProduct_title(rs.getString("product_title"));
                    item.getProduct().setDescription(rs.getString("product_desc"));
                    item.getProduct().setProductPrice(rs.getDouble("product_price"));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cart;
    }

    @Override
    public double getTotalCartPrice(List<CartLineItem> cart) {
        double sum = 0;
        try {
            for (CartLineItem item : cart) {
                pst = con.prepareStatement("SELECT product_price FROM products WHERE product_id=?");
                pst.setInt(1, item.getProduct().getId());
                rs = pst.executeQuery();
                while (rs.next()) {
                    double productPrice = rs.getDouble("product_price");
                    int quantity = item.getQuantity();
                    double totalPrice = productPrice * quantity;
                    sum += totalPrice;
                    item.getProduct().setProductPrice(productPrice);
                }
            }

        } catch (Exception ex) {
            System.out.println("Error" + ex.getMessage());
        }
        return sum;
    }

}
