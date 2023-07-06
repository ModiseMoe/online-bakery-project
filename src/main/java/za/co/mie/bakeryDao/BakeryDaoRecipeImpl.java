package za.co.mie.bakeryDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DateFormatter;
import za.co.mie.model.Ingridient;
import za.co.mie.model.Recipe;

public class BakeryDaoRecipeImpl implements BakeryDaoRecipeInterface {

    private Connection con = null;
    private PreparedStatement pst;
    private PreparedStatement ps;
    private ResultSet rs = null;

    public BakeryDaoRecipeImpl(Connection con) {
        this.con = con;
    }

    @Override
    public boolean addRecipe(Recipe recipe) {
        boolean retVal = false;
        boolean inOk = true;

        if (con != null && recipe != null) {

            try {
                con.setAutoCommit(false);
                pst = con.prepareStatement("INSERT INTO recipe(recipe_name,description) values(?,?)");
                pst.setString(1, recipe.getRecipeName());
                pst.setString(2, recipe.getDescription());
                if (pst.executeUpdate() > 0) {
                    pst = con.prepareStatement("SELECT LAST_INSERT_ID()");
                    rs = pst.executeQuery();
                    int recipeId = recipe.getRecipeId();
                    if (rs.next()) {
                        recipeId = rs.getInt(1);
                    }
                    pst = con.prepareStatement("INSERT INTO recipe_ingredient (recipe_id, ingredient_id ,quantity) VALUES(?,?,?) ");
                    for (Ingridient ingredient : recipe.getIngridients()) {
                        pst.setInt(1, recipeId);
                        pst.setInt(2, ingredient.getIngredientId());
                        pst.setInt(3, ingredient.getMinimumStockQuantity());
                        if (pst.executeUpdate() < 1) {
                            con.rollback();
                            inOk = false;
                            break;
                        }
                    }
                }
                if (inOk) {
                    con.commit();
                    retVal = true;
                }
            } catch (SQLException ex) {
                try {
                    con.rollback();
                } catch (SQLException ex1) {
                }
                System.out.println(" add ingredient Error!!: " + ex.getMessage());
            } finally {
                try {
                    con.setAutoCommit(true);
                } catch (SQLException ex) {
                }
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
    public boolean updateRecipe(Recipe recipe, int recipeId) {
        boolean retVal = false;
        if (con != null && recipe != null) {
            try {
                pst = con.prepareStatement("UPDATE recipe SET recipe_name=?,description=? WHERE recipe_id=?");

                pst.setString(1, recipe.getRecipeName());
                pst.setString(2, recipe.getDescription());
                pst.setInt(3, recipeId);
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
    public List<Recipe> getAllRecipes() {
        List<Recipe> allRecipes = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM recipe");
                rs = ps.executeQuery();
                while (rs.next()) {
                    allRecipes.add(new Recipe(rs.getString("recipe_name"), rs.getString("description"), rs.getInt("recipe_id"), rs.getBoolean("status")));
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
        return allRecipes;
    }

    @Override
    public Recipe getSingleRecipe(int recipeId) {
        Recipe recipe = new Recipe();
        if (con != null) {
            try {
                ps = con.prepareStatement(" SELECT recipe_name, description, recipe_id FROM recipe WHERE recipe_id=?");
                ps.setInt(1, recipeId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    recipe = new Recipe(rs.getString("recipe_name"), rs.getString("description"), rs.getInt("recipe_id"));
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
        return recipe;
    }

    @Override
    public boolean addProductToRecipe(int productId, int recipeId) {
        boolean retVal = false;
        if (con != null) {
            try {
                con.setAutoCommit(false);
                pst = con.prepareStatement("UPDATE products SET recipe_id=? WHERE product_id=?");
                pst.setInt(1, recipeId);
                pst.setInt(2, productId);
                if (pst.executeUpdate() > 0) {
                    ps = con.prepareStatement("UPDATE recipe SET product_id=? WHERE recipe_id=?");
                    ps.setInt(1, productId);
                    ps.setInt(2, recipeId);
                    if (ps.executeUpdate() > 0) {
                        con.commit();
                        retVal = true;
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
    public int getRecipeId(String recipe_name) {
        int productId = 0;
        if (con != null) {
            try {
                ps = con.prepareStatement(" SELECT recipe_id FROM recipe WHERE recipe_name= ?");
                ps.setString(1, recipe_name);
                rs = ps.executeQuery();
                while (rs.next()) {
                    productId = rs.getInt("recipe_id");
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
    public boolean activateRecipe(int recipe_id) {
        boolean retVal = false;
        if (con != null && recipe_id > 0) {
            try {
                pst = con.prepareStatement("UPDATE recipe SET status=? WHERE recipe_id=?");
                pst.setBoolean(1, true);
                pst.setInt(2, recipe_id);
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
    public boolean disableRecipe(int recipe_id) {
        boolean retVal = false;
        if (con != null && recipe_id > 0) {
            try {
                pst = con.prepareStatement("UPDATE recipe SET status=? WHERE recipe_id=?");
                pst.setBoolean(1, false);
                pst.setInt(2, recipe_id);
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
    public List<String> getIngredientsForRecipe(int recipeId) {
        List<String> ingr_name = new ArrayList();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT ingredients.ingredient_name FROM recipe_ingredient JOIN ingredients ON recipe_ingredient.ingredient_id = ingredients.ingredient_id WHERE recipe_ingredient.recipe_id = ?");

                ps.setInt(1, recipeId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ingr_name.add(rs.getString("ingredient_name"));
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
        return ingr_name;
    }

    //****************************************************************************************
    public static void main(String[] args) {
        Date ld = Date.valueOf(LocalDate.of(2023, 06, 23));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println(sdf.format(ld));
        Connection connn = null;
        System.out.println("inside main method");
        try {
            try {
                System.out.println("inside ptyrt try");
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BakeryDaoRecipeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("inside try");
            connn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bakeryDB", "root", "root");
        } catch (SQLException ex) {
            Logger.getLogger(BakeryDaoRecipeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (connn != null) {
            System.out.println("Got connection");
            BakeryDaoRecipeInterface bakeryDao = new BakeryDaoRecipeImpl(connn);
            BakeryDaoIngredientsInterface bdii = new BakeryDaoIngredientsImpl(connn);
            List<Ingridient> myList = new ArrayList();
            List<Ingridient> ingrList = bdii.getAllIngridients();
            for (Ingridient list : ingrList) {
                if (list.getIngridientsName().equals("Milk") || list.getIngridientsName().equals("Flour") || list.getIngridientsName().equals("dff")) {
                    list.setMinimumStockQuantity(2);
                    myList.add(new Ingridient(list.getIngridientsName(), list.getMinimumStockQuantity(), list.getIngredientId()));
                }

            }
            System.out.println(myList);
            boolean val = bakeryDao.addRecipe(new Recipe("mine", "zzzzhhhh", myList));
            if (val == true) {
                System.out.println("added");
            } else {
                System.out.println("not added");
            }

            //int d =  bakeryDao.getRecipeId("Black forest");
            //System.out.println(d);
            //  Recipe recipe = bakeryDao.getSingleRecipe(22);
            // System.out.println(recipe);
            //*************************************************
//            List<Recipe> recipess = bakeryDao.getAllRecipes();
//            for (Recipe mylist : recipess) {
//                System.out.println(mylist);
//            }
            //  List<String> namess = bakeryDao.getIngredientsForRecipe(34);
            // for (String mylist : namess) {
            //     System.out.println(mylist);
            //}
            //****************************************************
        }
    }

}
