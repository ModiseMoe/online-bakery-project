package za.co.mie.model;
import com.itextpdf.text.Document;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;

import java.util.Properties;

public class Email {
    private final String senderEmail;
    private final String senderPassword;

    public Email(String senderEmail, String senderPassword) {
        this.senderEmail = senderEmail;
        this.senderPassword = senderPassword;
    }

    public Email() {
        this("topieforbakery2@gmail.com","jmswyppsiiadpexj");
    }

    public boolean sendEmail(String recipientEmail, String subject, String body) throws MessagingException {
        boolean sentEmail = false;
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Email sent successfully!");
            sentEmail = true;
        } catch (MessagingException e) {
            e.printStackTrace();
            throw e;
        }
        return sentEmail;
    }

    public boolean sendRegistrationEmail(String userName, String recipientEmail ) throws MessagingException {
        String subject = "Welcome to ToPieFor Online Bakery - Account Registration Confirmation";
        StringBuilder bodyBuilder = new StringBuilder();

        bodyBuilder.append("Dear ").append(userName).append(",\n\nThank you for joining ToPieFor Online Bakery! You have succesfully registered an account.")
                .append(" Click On the link below to start your shopping journey:\n")
                .append("http://localhost:8080/BakeryProject/login.jsp")
                .append("\n")
                .append("For any questions assistance, please contact our support team at (011)415-3459.\n\n")
                .append("Welcome aboard\n\nToPieFor Bakery Team");
        String body = bodyBuilder.toString();

        return sendEmail(recipientEmail, subject, body);
    }

    public void sendPasswordRecoveryEmail(String recipientEmail, String confirmationCode) throws MessagingException {
        String subject = "ToPieFor Password Recovery System";
        StringBuilder bodyBuilder = new StringBuilder();
        bodyBuilder.append("Click the following link to reset your password:\n")
                .append("To activate your account, enter the following code on the link below: ")
                .append(confirmationCode)
                .append("\nLink: ")
                .append("http://localhost:8080/BakerySystem/")
                .append("\nIf you have any questions or need assistance, please contact our support team at 0607629075.\n")
                .append("Welcome aboard\nToPieFor Bakery Team");

        String body = bodyBuilder.toString();

        sendEmail(recipientEmail, subject, body);
    }
    
    public boolean sendOrderEmail(String userName,int order_id ,String recipientEmail ) throws MessagingException {
        String subject = "Welcome  to ToPieFor Online Bakery";
        StringBuilder bodyBuilder = new StringBuilder();

        bodyBuilder.append("Dear ").append(userName).append(",\n\nThank you for choosing our services/products! We are pleased to inform you that we have received your order and are currently processing it.\n")
                .append(" This email serves as a confirmation of your purchase and provides important details regarding your order. ").append("order#").append(order_id)
                .append(" Click On the link below to view your order:\n")
                .append("http://localhost:8080/BakeryProject/ShowOrders")
                .append("\n")
                .append("For any questions assistance, please contact our support team at (011)415-3459.\n\n")
                .append("Welcome aboard\n\nToPieFor Bakery Team");
        String body = bodyBuilder.toString();

        return sendEmail(recipientEmail, subject, body);
    }
    
     public boolean sendReceiptEmail(String userName,int order_id ,String recipientEmail) throws MessagingException {
        String subject = "Welcome  to ToPieFor Online Bakery";
        StringBuilder bodyBuilder = new StringBuilder();

        bodyBuilder.append("Dear ").append(userName).append(",\n\nThank you for choosing our services/products! We are pleased to inform you that we have received your payment for.\n").append("order#").append(order_id)
                .append(" This email serves as a confirmation of your purchase and provides important details regarding your order. ")
                .append(" Click on the link below to download your Receipt:\n")
                .append("http://localhost:8080/BakeryProject/Receipt.jsp")
                .append("\n")
                .append("For any questions assistance, please contact our support team at (011)415-3459.\n\n")
                .append("Welcome aboard\n\nToPieFor Bakery Team");
        String body = bodyBuilder.toString();

        return sendEmail(recipientEmail, subject, body);
    }
     
     public boolean sendfailedPurchaseEmail(String userName,int order_id ,String recipientEmail) throws MessagingException {
        String subject = "Welcome  to ToPieFor Online Bakery";
        StringBuilder bodyBuilder = new StringBuilder();

        bodyBuilder.append("Dear ").append(userName).append(",\n\nThank you for choosing our services/products! Unfortunately your payment for .\n").append("order#").append(order_id).append("was not successfull")
                .append(" This email serves as a confirmation of your purchase and provides important details regarding your order. ")
                .append(" Click on the link below to Pay for your order:\n")
                .append("http://localhost:8080/BakeryProject/login.jsp")
                .append("\n")
                .append("For any questions assistance, please contact our support team at (011)415-3459.\n\n")
                .append("Welcome aboard\n\nToPieFor Bakery Team");
        String body = bodyBuilder.toString();

        return sendEmail(recipientEmail, subject, body);
    }
    
}
