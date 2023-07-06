package za.co.mie.model;

import java.util.Objects;

public class Address {

    private String address_details;
    private String city;
    private String postal_code;
    private int address_id;
    private String country;

    public Address(String address_details, String city, String postal_code, String country) {
        this.address_details = address_details;
        this.city = city;
        this.postal_code = postal_code;
        this.country = country;
    }

    public Address(String address_details, String city, String postal_code, int address_id, String country) {
        this.address_details = address_details;
        this.city = city;
        this.postal_code = postal_code;
        this.address_id = address_id;
        this.country = country;
    }

    public Address() {
    }

    public String getAddress_details() {
        return address_details;
    }

    public void setAddress_details(String address_details) {
        this.address_details = address_details;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.address_details);
        hash = 59 * hash + Objects.hashCode(this.city);
        hash = 59 * hash + Objects.hashCode(this.postal_code);
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
        final Address other = (Address) obj;
        if (!Objects.equals(this.address_details, other.address_details)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.postal_code, other.postal_code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Address{" + "address_details=" + address_details + ", city=" + city + ", postal_code=" + postal_code + ", address_id=" + address_id + ", country=" + country + '}';
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
