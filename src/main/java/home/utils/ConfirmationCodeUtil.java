package home.utils;

import home.security.domain.ConfirmationCode;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ConfirmationCodeUtil extends Repos {

    public int generateConfirmationCode(String email){
        Random rand = new Random();
        int randomNum = new Random().nextInt(8888) + 1111;
        int mode = randomNum % 8;
        return randomNum - mode;
    }

    public ConfirmationCode saveConfirmationCode(String useriId, int confirmationCode){
        return confirmationCodeRepo.save(new ConfirmationCode(useriId,confirmationCode));
    }
}
