package home.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import home.entity.User;
import home.security.domain.LoginType;
import home.security.domain.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import static home.security.SecurityConstants.*;
import static home.security.SecurityConstants.GOOGLE;

@Component
public class Social extends Repos {

    private HttpResult httpResult;

    private TokenUtil tokenUtil;

    private InputOutput inputOutput;

    private JsonUtil jsonUtil;

    @Autowired
    public Social(HttpResult httpResult, TokenUtil tokenUtil, InputOutput inputOutput, JsonUtil jsonUtil) {
        this.httpResult = httpResult;
        this.tokenUtil = tokenUtil;
        this.inputOutput = inputOutput;
        this.jsonUtil = jsonUtil;
    }

    public Object socialSignInOrSignUp(int type, String socialToken){
        String result;
        String userId;
        String fullName;

        try{
            if(type == GOOGLE){
                result = verifySocialToken(socialToken,URL_CHECK_TOKEN_GOOGLE);
            }else if(type == FACEBOOK){
                result = verifySocialToken(socialToken,URL_CHECK_TOKEN_FACEBOOK);
            }else{
                return httpResult.unauthorized("invalid social type");
            }

            if(result == null){
                return httpResult.unauthorized("social server did not respond");
            }

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(result, JsonObject.class);

            if(type == GOOGLE){
                userId = jsonObject.get("id").getAsString();
                String firstname = jsonUtil.getStringFromJsonObject(jsonObject,"given_name","unknown");
                String lastname = jsonUtil.getStringFromJsonObject(jsonObject,"family_name","unknown");
                fullName = firstname + " " + lastname;
            }else{
                fullName = jsonObject.get("name").getAsString();
                userId = jsonObject.get("id").getAsString();
            }

            if(userRepo.exists(userId)){
                if(tokenRepo.exists(userId)){
                    Token token = tokenRepo.findOne(userId);
                    return httpResult.ok("token",token.getToken());
                }else{
                    String token = tokenUtil.generateToken(userId);
                    return httpResult.ok("token",token);
                }
            }else{
                if(type == GOOGLE){
                    userRepo.save(new User(userId,fullName, LoginType.Google));
                    return httpResult.ok("token", tokenUtil.generateToken(userId));
                }else{
                    userRepo.save(new User(userId,fullName, LoginType.Facebook));
                    return httpResult.ok("token", tokenUtil.generateToken(userId));
                }

            }
        }catch (Exception e){
            e.printStackTrace();
            return httpResult.serverError(e.getMessage());
        }
    }

    private String verifySocialToken(String idToken, String verificationUrl) throws Exception{
        String tokenUrl = verificationUrl + idToken;
        URL url = new URL(tokenUrl);
        HttpURLConnection httpURLConn = (HttpURLConnection)url.openConnection();
        int responseCode = httpURLConn.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK){
            InputStream inputStream = httpURLConn.getInputStream();
            return inputOutput.inputStreamToStringWithByteArrayOutputStream(inputStream);
        }
        return null;
    }
}
