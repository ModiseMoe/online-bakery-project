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
import za.co.mie.model.Ingridient;
import za.co.mie.model.Unit;

public class BakeryDaoIngredientsImpl implements BakeryDaoIngredientsInterface {

    private Connection con = null;
    private PreparedStatement pst;
    private PreparedStatement ps;
    private ResultSet rs = null;

    public BakeryDaoIngredientsImpl(Connection con) {
        this.con = con;
    }

    @Override
    public boolean addUnit(Unit unit) {
        boolean retVal = false;
        if (con != null && unit != null) {
            try {
                pst = con.prepareStatement("INSERT INTO units (unit_name) value(?)");
                pst.setString(1, unit.getUnit_name());
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
    public boolean updateUnit(Unit unit, int unitId) {
        boolean retVal = false;
        if (con != null && unitId > 0) {
            try {
                pst = con.prepareStatement("UPDATE units SET unit_name=?  WHERE unit_id=?");
                pst.setString(1, unit.getUnit_name());
                pst.setInt(2, unitId);
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
    public List<Unit> getAllUnits() {
        List<Unit> allUnits = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM units ");
                rs = ps.executeQuery();
                while (rs.next()) {
                    allUnits.add(new Unit(rs.getString("unit_name"), rs.getInt("unit_id")));
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
        return allUnits;
    }

    @Override
    public Unit getSingleUnit(int unitId) {
        Unit unit = new Unit();
        if (con != null) {
            try {
                ps = con.prepareStatement(" SELECT unit_name, unit_id FROM units WHERE unit_id=?");
                ps.setInt(1, unitId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    unit = new Unit(rs.getString("unit_name"), rs.getInt("unit_id"));
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
        return unit;
    }

    @Override
    public boolean addIngredient(Ingridient ingredient, int unitId) {
        boolean retVal = false;
        if (con != null && ingredient != null) {
            try {
                pst = con.prepareStatement("INSERT INTO ingredients (ingredient_name,quantityOnHand,minimumStockQty,unit_id) value(?,?,?,?)");
                pst.setString(1, ingredient.getIngridientsName());
                pst.setInt(2, ingredient.getQuantityOnHand());
                pst.setInt(3, ingredient.getMinimumStockQuantity());
                pst.setInt(4, unitId);
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
    public boolean updateIngredient(Ingridient ingridient, int ingridientId) {
        boolean retVal = false;
        if (con != null && ingridientId > 0) {
            try {
                pst = con.prepareStatement("UPDATE ingredients SET ingredient_name=?,quantityOnHand=?,minimumStockQty=?  WHERE ingredient_id=?");
                pst.setString(1, ingridient.getIngridientsName());
                pst.setInt(2, ingridient.getQuantityOnHand());
                pst.setInt(3, ingridient.getMinimumStockQuantity());
                pst.setInt(4, ingridientId);
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
    public List<Ingridient> getAllIngridients() {
        List<Ingridient> allIngridients = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM ingredients ");
                rs = ps.executeQuery();
                while (rs.next()) {
                    allIngridients.add(new Ingridient(rs.getString("ingredient_name"), rs.getInt("quantityOnHand"), rs.getInt("minimumStockQty"), rs.getInt("ingredient_id"), rs.getInt("unit_id")));
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
        return allIngridients;
    }

    @Override
    public Ingridient getSingleIngridient(int ingridientId) {
        Ingridient ingridient = new Ingridient();
        if (con != null) {
            try {
                ps = con.prepareStatement(" SELECT ingredient_name, quantityOnHand,minimumStockQty,ingredient_id,unit_id FROM ingredients WHERE ingredient_id=?");
                ps.setInt(1, ingridientId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ingridient = new Ingridient(rs.getString("ingredient_name"), rs.getInt("quantityOnHand"), rs.getInt("minimumStockQty"), rs.getInt("ingredient_id"), rs.getInt("unit_id"));
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
        return ingridient;
    }

    public String getUnitForIngredient(int ingredient_id) {
    String unitName = null;
    if (con != null) {
        try {
            ps = con.prepareStatement("SELECT units.unit_name FROM units JOIN ingredients ON units.unit_id = ingredients.unit_id WHERE ingredients.ingredient_id = ?");
            ps.setInt(1, ingredient_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                unitName = rs.getString("unit_name");
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
    return unitName;
}


    //**************************************************************************************************************************************
    public static void main(String[] args) {
        Connection connn = null;
        System.out.println("inside main method");
        try {
            try {
                System.out.println("inside ptyrt try");
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BakeryDaoIngredientsImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("inside try");
            connn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bakeryDB", "root", "root");
        } catch (SQLException ex) {
            Logger.getLogger(BakeryDaoIngredientsImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (connn != null) {
            System.out.println("Got connection");
            BakeryDaoIngredientsInterface bakeryDao = new BakeryDaoIngredientsImpl(connn);

            // int d =  bakeryDao.getRecipeId("Black forest");
            // System.out.println(d);
            //Ingridient ingr = bakeryDao.getSingleIngridient(1);
            System.out.println(bakeryDao.getUnitForIngredient(1));

            // System.out.println("id : " + unit.getUnitId());
            //*************************************************
//            List<Unit> units = bakeryDao.getAllUnits();
//            for (Unit mylist : units) {
//                System.out.println(mylist);
//                System.out.println(mylist.getUnitId());
//            }
            //****************************************************
        }
    }

    @Override
    public List<Ingridient> getAllOutOfStockIngredients() {
        List<Ingridient> ingr = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM ingredients WHERE quantityOnHand < minimumStockQty ");
                rs = ps.executeQuery();
                while (rs.next()) {
                    ingr.add(new Ingridient(rs.getString("ingredient_name"), rs.getInt("quantityOnHand"), rs.getInt("minimumStockQty"), rs.getInt("ingredient_id"), rs.getInt("unit_id")));
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
        return ingr;
    }

}
