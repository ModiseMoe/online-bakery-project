
package za.co.mie.model;

import java.util.Objects;

public class PaymentDetails {
    private String cardholder_name;
    private String cardNumber;
    private String expireDate;
    private int payment_id;
    private String cvv;

    public PaymentDetails(String cardholder_name, String cardNumber, String expireDate, String cvv) {
        this.cardholder_name = cardholder_name;
        this.cardNumber = cardNumber;
        this.expireDate = expireDate;
        this.cvv = cvv;
    }

    public PaymentDetails(String cardholder_name, String cardNumber, String expireDate, int payment_id, String cvv) {
        this.cardholder_name = cardholder_name;
        this.cardNumber = cardNumber;
        this.expireDate = expireDate;
        this.payment_id = payment_id;
        this.cvv = cvv;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.cardholder_name);
        hash = 19 * hash + Objects.hashCode(this.cardNumber);
        hash = 19 * hash + Objects.hashCode(this.expireDate);
        hash = 19 * hash + this.payment_id;
        hash = 19 * hash + Objects.hashCode(this.cvv);
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
        final PaymentDetails other = (PaymentDetails) obj;
        if (this.payment_id != other.payment_id) {
            return false;
        }
        if (!Objects.equals(this.cardholder_name, other.cardholder_name)) {
            return false;
        }
        if (!Objects.equals(this.cardNumber, other.cardNumber)) {
            return false;
        }
        if (!Objects.equals(this.expireDate, other.expireDate)) {
            return false;
        }
        if (!Objects.equals(this.cvv, other.cvv)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PaymentDetails{" + "cardholder_name=" + cardholder_name + ", cardNumber=" + cardNumber + ", expireDate=" + expireDate + ", payment_id=" + payment_id + ", cvv=" + cvv + '}';
    }

    
    public String getCardholder_name() {
        return cardholder_name;
    }

    public void setCardholder_name(String cardholder_name) {
        this.cardholder_name = cardholder_name;
    }

   
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

   
    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    
    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public String getCvv() {
        return cvv;
    }

   
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    
    
    
}
