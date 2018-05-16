package home.utils;

import home.security.exception.InvalidEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Component
public class EmailUtil {

    @Autowired
    JavaMailSenderImpl mailSender;

    public void sendConfirmationCode(String email, int confirmationCode)throws Exception{
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("info@gotest.uz");
        message.setTo(email);
        message.setSubject("Your confirmation code");
        message.setText(confirmationCode + "");
        mailSender.send(message);
    }

    public void sendPassword(String email, String password){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("info@gotest.uz");
        message.setTo(email);
        message.setSubject("Your password");
        message.setText(password + "");
        mailSender.send(message);
    }

    public void isValidEmailAddress(String email) throws InvalidEmailException {
        try{
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();

        }catch (AddressException e){
            throw new InvalidEmailException(email);
        }
    }
}
