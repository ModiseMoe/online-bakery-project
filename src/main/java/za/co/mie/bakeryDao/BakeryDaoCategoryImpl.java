package za.co.mie.bakeryDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.mie.model.Category;

public class BakeryDaoCategoryImpl implements BakeryDaoCategoryInterface {

    private Connection con = null;
    private PreparedStatement pst;
    private PreparedStatement ps;
    private ResultSet rs = null;

    public BakeryDaoCategoryImpl(Connection con) {
        this.con = con;
    }
    
     @Override
    public boolean disableCategory(int cat_id) {
        boolean retVal = false;
        if (con != null && cat_id >0 ) {
            try {
                pst = con.prepareStatement("UPDATE category SET status=? WHERE cat_id=?");
                pst.setBoolean(1, false);
                pst.setInt(2, cat_id);
                retVal = pst.executeUpdate() > 0;
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
        return retVal;
    }

    @Override
    public boolean activateCategory(int cat_id) {
        boolean retVal = false;
        if (con != null && cat_id >0 ) {
            try {
                pst = con.prepareStatement("UPDATE category SET status=? WHERE cat_id=?");
                pst.setBoolean(1, true);
                pst.setInt(2, cat_id);
                retVal = pst.executeUpdate() > 0;
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
        return retVal;
    }

    @Override
    public boolean addCategory(Category category) {
        boolean retVal = false;
        if (con != null && category != null) {
            try {
                pst = con.prepareStatement("INSERT INTO category(category_name,product_id) values(?,null)");
                pst.setString(1, category.getCategoryName());
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
    public Category getSingleCategory(int cat_id) {
        
       Category category = new Category();
        if (con != null) {
            try {
                ps = con.prepareStatement(" SELECT category_name, cat_id FROM category WHERE cat_id=?");
                ps.setInt(1,cat_id );
                rs = ps.executeQuery();
                while (rs.next()) {
                    category = new Category(rs.getString("category_name") , rs.getInt("cat_id"));
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
        return category;

    }

    @Override
    public boolean updateCategory(int cat_id, String category_name) {
        boolean retVal = false;
        if (con != null && cat_id > 0) {
            try {
                pst = con.prepareStatement("UPDATE category SET category_name=?  WHERE cat_id=?");
                pst.setString(1, category_name);
                pst.setInt(2, cat_id);
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
                      pst = null;
                    
                }
            }
        }
        return retVal;
    }

    @Override
    public boolean deleteCategory(Category category) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> allCategories = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM category ");
                rs = ps.executeQuery();
                while (rs.next()) {
                    allCategories.add(new Category(rs.getString("category_name") ,rs.getBoolean("status") ,rs.getInt("cat_id")));
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
        return allCategories;
    }
    
     @Override
    public List<Category> getAllCategoriesByStatus() {
        List<Category> allCategories = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM category WHERE status=true ");
                rs = ps.executeQuery();
                while (rs.next()) {
                    allCategories.add(new Category(rs.getString("category_name") ,rs.getBoolean("status") ,rs.getInt("cat_id")));
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
        return allCategories;
    }
    

    @Override
    public int getCategoryId(Category category) {
        int catId = 0;
        if (con != null) {
            try {
                ps = con.prepareStatement(" SELECT cat_id FROM category WHERE category_name= ?");
                ps.setString(1, category.getCategoryName());

                rs = ps.executeQuery();
                while (rs.next()) {
                    catId = rs.getInt("cat_id");
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
        return catId;
    }
}
//********************************************************************************************************************

