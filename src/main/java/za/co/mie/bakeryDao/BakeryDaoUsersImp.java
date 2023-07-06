package za.co.mie.bakeryDao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import za.co.mie.model.*;

public class BakeryDaoUsersImp implements BakeryDaoUsersInterface {

    private Connection con = null;
    private PreparedStatement pst;
    private PreparedStatement ps;
    private ResultSet rs = null;

    public BakeryDaoUsersImp(Connection con) {
        this.con = con;
    }

    public List<User> getAllUsers() {
                List<User> allUsers = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT * FROM user");
                rs = ps.executeQuery();
                while (rs.next()) {
                    allUsers.add(new User(rs.getString("user_name"), rs.getString("user_pwd"), rs.getString("user_surname"), rs.getString("user_idNo"), rs.getString("user_role"), rs.getString("user_mobileNum"), rs.getString("user_emailAdd"),rs.getBoolean("status")));
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
        return allUsers;
    }

    @Override
    public User getSingleUser(String email_add) {
         User user = new User();
        if (con != null) {
            try {
                ps = con.prepareStatement(" SELECT * FROM user WHERE user_emailAdd=?");
                ps.setString(1,  email_add);
                rs = ps.executeQuery();
                while (rs.next()) {
                    user = new User(rs.getString("user_name") ,rs.getString("user_pwd") , rs.getString("user_surname") , rs.getString("user_idNo") ,rs.getString("user_role"), rs.getString("user_mobileNum") , rs.getString("user_emailAdd"));
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
        return user;
    }
       
    

    @Override
    public boolean addUser(User user) {
        boolean retVal = false;
        if (con != null && user != null) {
            try {
                pst = con.prepareStatement("INSERT INTO user(user_name,user_pwd,user_surname,user_idNo,user_role,user_mobileNum,user_emailAdd, address_id) values(?,?,?,?,?,?,?,null)");

                pst.setString(1, user.getName());
                pst.setString(2, user.getPassword());
                pst.setString(3, user.getSurname());
                pst.setString(4, user.getId_number());
                pst.setString(5, user.getUserRole());
                pst.setString(6, user.getMobileNumber());
                pst.setString(7, user.getEmailAddr());
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
    public boolean updateUser(String email_add ,User user ) {
        boolean retVal = false;
        if (con != null && email_add != null) {

            try {
                pst = con.prepareStatement("UPDATE user SET user_name = ?, user_pwd = ?, user_surname = ? , user_idNo = ? , user_mobileNum = ? , user_emailAdd = ? WHERE user_emailAdd = ? ");
               
                pst.setString(1, user.getName());
                pst.setString(2, user.getPassword());
                pst.setString(3, user.getSurname());
                pst.setString(4, user.getId_number());
                pst.setString(5, user.getMobileNumber());
                pst.setString(6, user.getEmailAddr());
                pst.setString(7, email_add);

                if (pst.executeUpdate() > 0) {
                    retVal = true;
                }

            } catch (SQLException ex) {
                System.out.println("Error" + ex.getMessage());

            } finally {
                if (pst != null) {
                    try {
                        pst.close();

                    } catch (SQLException ex) {
                        System.out.println("failed to close " + ex.getMessage());
                    }
                }

            }
           
        }
     return retVal;
    }

    @Override
   public User login(User user) {
    User aUser = null;
    if (con != null) {
        try {
            ps = con.prepareStatement("SELECT user_name, user_pwd, user_surname, user_idNo, user_role, user_mobileNum, user_emailAdd, status, address_id FROM user WHERE user_emailAdd = ?");
            ps.setString(1, user.getEmailAddr().toLowerCase());
            rs = ps.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("user_pwd");
                
                if (BCrypt.checkpw(user.getPassword(), storedPassword)) {
                    aUser = new User(
                        rs.getString("user_name"),
                        storedPassword, 
                        rs.getString("user_surname"),
                        rs.getString("user_idNo"),
                        rs.getString("user_role"),
                        rs.getString("user_mobileNum"),
                        rs.getString("user_emailAdd"),
                        rs.getBoolean("status")
                    );
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error!!: " + ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("Could not close the ResultSet: " + ex.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Could not close the PreparedStatement: " + ex.getMessage());
                }
            }
        }
    }
    return aUser;
}

    @Override
    public boolean activateUser(String email) {
                boolean retVal = false;
        if (con != null && email !=null) {
            try {
                pst = con.prepareStatement("UPDATE user SET status=? WHERE user_emailAdd=?");
                pst.setBoolean(1, true);
                pst.setString(2, email);
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
    public boolean disableUser(String email) {
                boolean retVal = false;
        if (con != null && email != null) {
            try {
                pst = con.prepareStatement("UPDATE user SET status=? WHERE user_emailAdd=?");
                pst.setBoolean(1, false);
                pst.setString(2, email);
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

   
}
