
package za.co.mie.model;

import java.util.Objects;

public class User {
    private String name;
    private String surname;
    private String password;
    private String emailAddr;
    private String id_number;
    private String userRole;
    private String mobileNumber;
    private boolean status;
    private Address address;

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", surname=" + surname + ", password=" + password + ", emailAddr=" + emailAddr + ", id_number=" + id_number + ", userRole=" + userRole + ", mobileNumber=" + mobileNumber + ", status=" + isStatus() + '}';
    }
   
   

     public User(String name,String password, String surname ,String id_number,String userRole,String mobileNumber, String emailAddr  ,boolean status) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.emailAddr = emailAddr;
        this.id_number = id_number;
        this.userRole = userRole;
        this.mobileNumber = mobileNumber;
        this.status = status;
        
    }
    
    public User(String name,String password, String surname ,String id_number,String userRole,String mobileNumber, String emailAddr ) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.emailAddr = emailAddr;
        this.id_number = id_number;
        this.userRole = userRole;
        this.mobileNumber = mobileNumber;
        
    }

    public User(String name, String surname, String password, String emailAddr, String id_number, String mobileNumber) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.emailAddr = emailAddr;
        this.id_number = id_number;
        this.mobileNumber = mobileNumber;
    }
    
    

    public User(String password, String emailAddr) {
        this.password = password;
        this.emailAddr = emailAddr;
    }

    public User(String emailAddr) {
        this.emailAddr = emailAddr;
    }
   
    

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.surname);
        hash = 71 * hash + Objects.hashCode(this.password);
        hash = 71 * hash + Objects.hashCode(this.emailAddr);
        hash = 71 * hash + Objects.hashCode(this.id_number);
        hash = 71 * hash + Objects.hashCode(this.userRole);
        hash = 71 * hash + Objects.hashCode(this.mobileNumber);
     
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.emailAddr, other.emailAddr)) {
            return false;
        }
        if (!Objects.equals(this.id_number, other.id_number)) {
            return false;
        }
        if (!Objects.equals(this.userRole, other.userRole)) {
            return false;
        }
        if (!Objects.equals(this.mobileNumber, other.mobileNumber)) {
            return false;
        }
       
        return true;
    }

   
    public boolean isStatus() {
        return status;
    }

    
    
    
    
}
