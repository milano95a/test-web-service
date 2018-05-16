package home.utils;

import home.security.domain.Token;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;

@Component
public class TokenUtil extends Repos {

    public String generateToken(String userId){

        Token dbToken = tokenRepo.findTokenByUserId(userId);
        BigInteger bigInteger = new BigInteger(130, new SecureRandom());
        String generatedToken = bigInteger.toString(32);

        if(dbToken != null){
            tokenRepo.delete(dbToken.getToken());
            dbToken = new Token(generatedToken,userId);
            dbToken.setToken(generatedToken);
        }else{
            dbToken = new Token(generatedToken,userId);
        }

        tokenRepo.save(dbToken);

        return generatedToken;
    }
}
