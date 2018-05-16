package home.security.controller;

import com.google.common.hash.Hashing;
import home.entity.User;
import home.security.domain.*;
import home.security.exception.InvalidEmailException;
import home.security.exception.NullEmailException;
import home.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Random;

import static home.security.SecurityConstants.*;

@RestController
@RequestMapping("/api")
public class AuthenticationController extends BaseAuthController {

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private Social social;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private ConfirmationCodeUtil confirmationCodeUtil;

    @Autowired
    HttpResult httpResult;

    @GetMapping("/user")
    public @ResponseBody
    Object currentUser(){
        return user();
    }

    @PostMapping("/authgoogle")
    public @ResponseBody Object authgoogle(@RequestBody  TokenRequest tokenRequest){
        return social.socialSignInOrSignUp(GOOGLE,tokenRequest.getToken());
    }

    @PostMapping("/authfacebook")
    public @ResponseBody Object authfacebook(@RequestBody TokenRequest tokenRequest){
        return social.socialSignInOrSignUp(FACEBOOK,tokenRequest.getToken());
    }

    @PostMapping(value = "/registeremail", produces = "application/json")
    public @ResponseBody
    Object registerEmail(@RequestBody RegisterEmail email){
        try {
            if(!userRepo.exists(email.getEmail())){

                emailUtil.isValidEmailAddress(email.getEmail());

                int confirmCode = confirmationCodeUtil.generateConfirmationCode(email.getEmail());

                emailUtil.sendConfirmationCode(email.getEmail(),confirmCode);

                if(confirmationCodeUtil.saveConfirmationCode(email.getEmail(),confirmCode) == null)
                    return httpResult.serverError("Failed to save confirmation code");

                return httpResult.ok("message","confirmation code sent to your email address");

            }else{
                return httpResult.badRequest("email already registered");
            }
        }catch (NullEmailException e){
            return httpResult.badRequest(e.getMessage());
        } catch (InvalidEmailException e){
            return httpResult.badRequest(e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
            return httpResult.serverError(e.getMessage());
        }
    }

    @PostMapping(value = "/confirmtoken")
    public @ResponseBody
    Object confirm(@RequestBody ConfirmationRequest confirmationRequest){
        try{
            if(confirmationCodeRepo.findConfirmationCodeByUserId(confirmationRequest.getEmail()) != null){
                Integer confirmationCode = confirmationCodeRepo.findConfirmationCodeByUserId(confirmationRequest.getEmail()).getConfirmationCode();

                int mode = confirmationCode % 8;

                if(!(mode > 2)){
                    int tokenMinusMode = confirmationCode - mode;
                    int givenConfirmaionCode = confirmationRequest.getConfirmationCode();
                    if(tokenMinusMode == givenConfirmaionCode){
                        if(userRepo.findOne(confirmationRequest.getEmail()) != null){
                            String token = tokenUtil.generateToken(confirmationRequest.getEmail());
                            return httpResult.ok("token",token + "");
                        }else{
                            String token = tokenUtil.generateToken(confirmationRequest.getEmail());
                            User user = new User(confirmationRequest.getEmail(),"", LoginType.Email);
                            userRepo.save(user);
                            return httpResult.ok("token",token + "");
                        }

                    }else{
                        Integer incrementedToken = confirmationCode + 1;
                        confirmationCodeUtil.saveConfirmationCode(confirmationRequest.getEmail(),incrementedToken);
                        return httpResult.badRequest("incorrect confirmation code");
                    }
                }else{
                    tokenRepo.delete(confirmationRequest.getEmail());
                    return httpResult.unauthorized("You have attempted more than 3 times");
                }
            }else{
                return httpResult.badRequest("Email is not registered");
            }
        }catch (Exception e){
            e.printStackTrace();
            return httpResult.serverError(e.getMessage());
        }
    }

    @PostMapping(value = "/completeregister")
    public @ResponseBody
    Object setUserNameAndPassword(@RequestBody UserDetails userDetails){
        try{
            User user = user();
            String encryptedPassword = Hashing.sha256().hashString(userDetails.getPassword(), StandardCharsets.UTF_8).toString();
            user.setUsername(userDetails.getUsername());
            user.setPassword(encryptedPassword);
            userRepo.save(user);

            String newToken = tokenUtil.generateToken(user.getUserId());
            return httpResult.ok("token",newToken);

        }catch (Exception e){
            return httpResult.serverError(e.getMessage());
        }
    }

    @PostMapping(value = "/authemail", produces = "application/json")
    public @ResponseBody
    Object signInWihEmail(@RequestBody EmailSignIn signInUser){
        try{
            if(userRepo.exists(signInUser.getEmail())){

                User user = userRepo.findOne(signInUser.getEmail());

                if(!(user.getLoginType() == LoginType.Email)){
                    return httpResult.unauthorized("User not found");
                }

                String hashed = SHA.hash(signInUser.getPassword());

                if(user.getPassword().equals(hashed)){
                    String token = null;

                    if(tokenRepo.exists(signInUser.getEmail())){
                        token = tokenRepo.findTokenByUserId(signInUser.getEmail()).getToken();
                    }else{
                        token = tokenUtil.generateToken(signInUser.getEmail());
                    }

                    return httpResult.ok("token",token);
                }else {
                    return httpResult.unauthorized("Passwords do not match");
                }
            }else {
                return httpResult.unauthorized("There is no user with the given email");
            }
        }catch (Exception e){
            e.printStackTrace();
            return httpResult.serverError(e.getMessage());
        }
    }

    @GetMapping(path = {"/unauth"})
    public @ResponseBody
    Object unauth(){
        tokenRepo.delete(user().getUserId());
        return httpResult.ok("message",user().getUserId() + " signed out");
    }

    @PostMapping(value = "/forgot", produces = "application/json")
    public Object forgotPassword(@RequestBody RegisterEmail email){
        try {
            if(tokenRepo.exists(tokenRepo.findTokenByUserId(email.getEmail()).getToken())){
                int confirmCode = confirmationCodeUtil.generateConfirmationCode(email.getEmail());
                tokenRepo.delete(tokenRepo.findTokenByUserId(email.getEmail()).getToken());
                emailUtil.sendConfirmationCode(email.getEmail(),confirmCode);
                confirmationCodeUtil.saveConfirmationCode(email.getEmail(),confirmCode);
                return httpResult.ok("message","Confirmation message sent to your email");
            }else{
                return httpResult.badRequest("email is not registered");
            }
        }catch (Exception e){
            e.printStackTrace();
            return httpResult.serverError(e.getMessage());
        }
    }

    @PostMapping(value = "/reset", produces = "application/json")
    public Object resetPassword(@RequestBody ResetPassword pass){
        try{
            User user = user();
            User dbUser = userRepo.findOne(user.getUserId());
            String newPassword = Hashing.sha256().hashString(pass.getPassword(),StandardCharsets.UTF_8).toString();
            dbUser.setPassword(newPassword);
            userRepo.save(dbUser);

            String token = tokenUtil.generateToken(dbUser.getUserId());
            return httpResult.ok("token",token);
        }catch (Exception e){
            return httpResult.serverError(e.getMessage());
        }
    }

//    private Object socialSignInOrSignUp(int type, String socialToken){
//        String result = null;
//        String userId = null;
//        String fullname = null;
//
//        try{
//            if(type == GOOGLE){
//                String url = URL_CHECK_TOKEN_GOOGLE;
//                result = verifyToken(socialToken,url);
//            }else if(type == FACEBOOK){
//                result = verifyToken(socialToken,URL_CHECK_TOKEN_FACEBOOK);
//            }else{
//                return unauthorized("invalid social type");
//            }
//
//            if(result == null){
//                return unauthorized("social server did not respond");
//            }
//
//            Gson gson = new Gson();
//            JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
//
//            if(type == GOOGLE){
//                userId = jsonObject.get("id").getAsString();
//                String firstname = getStringFromJsonObject(jsonObject,"given_name","unknown");
//                String lastname = getStringFromJsonObject(jsonObject,"family_name","unknown");
//                fullname = firstname + " " + lastname;
//            }else{
//                fullname = jsonObject.get("name").getAsString();
//                userId = jsonObject.get("id").getAsString();
//            }
//
//            if(userRepo.exists(userId)){
//                if(tokenRepo.exists(userId)){
//                    Token token = tokenRepo.findOne(userId);
//                    return ok("token",token.getToken());
//                }else{
//                    String token = generateToken(userId);
//                    return ok("token",token);
//                }
//            }else{
//                if(type == GOOGLE){
//                    userRepo.save(new User(userId,fullname, LoginType.Google));
//                    return ok("token",generateToken(userId));
//                }else{
//                    userRepo.save(new User(userId,fullname, LoginType.Facebook));
//                    return ok("token",generateToken(userId));
//                }
//
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return serverError(e.getMessage());
//        }
//    }

//    private String verifyToken(String idToken, String verificationUrl) throws Exception{
//        String tokenUrl = verificationUrl + idToken;
//        URL url = new URL(tokenUrl);
//        HttpURLConnection httpURLConn = (HttpURLConnection)url.openConnection();
//        int responseCode = httpURLConn.getResponseCode();
//        if(responseCode == HttpURLConnection.HTTP_OK){
//            InputStream inputStream = httpURLConn.getInputStream();
//            return inputStreamToStringWithByteArrayOutputStream(inputStream);
//        }
//        return null;
//    }

//    private String getStringFromJsonObject(JsonObject jsonObject, String memberName, String defaultString){
//        try {
//            String jsonString = jsonObject.get(memberName).getAsString();
//            return jsonString != null ? jsonString : defaultString;
//        }catch (Exception e){
//            return defaultString;
//        }
//    }

//    private String generateToken(String userId){
//
//        Token dbToken = tokenRepo.findTokenByUserId(userId);
//        BigInteger bigInteger = new BigInteger(130, new SecureRandom());
//        String generatedToken = bigInteger.toString(32);
//
//        if(dbToken != null){
//            tokenRepo.delete(dbToken.getToken());
//            dbToken = new Token(generatedToken,userId);
//            dbToken.setToken(generatedToken);
//        }else{
//            dbToken = new Token(generatedToken,userId);
//        }
//
//        tokenRepo.save(dbToken);
//
//        return generatedToken;
//    }

//    private String inputStreamToStringWithByteArrayOutputStream(InputStream inputStream) throws IOException {
//        ByteArrayOutputStream result = new ByteArrayOutputStream();
//        byte[] buffer = new byte[1024];
//        int length;
//        while ((length = inputStream.read(buffer)) != -1) {
//            result.write(buffer, 0, length);
//        }
//        return result.toString("UTF-8");
//    }

//    private void isValidEmailAddress(String email) throws InvalidEmailException {
//        try{
//            InternetAddress emailAddr = new InternetAddress(email);
//            emailAddr.validate();
//
//        }catch (AddressException e){
//            throw new InvalidEmailException(email);
//        }
//    }

//    private int generateConfirmationCode(String email){
//        Random rand = new Random();
//        int randomNum = new Random().nextInt(8888) + 1111;
//        int mode = randomNum % 8;
//        int confirmationCode = randomNum - mode;
//        return confirmationCode;
//    }

//    private ConfirmationCode saveConfirmationCode(String useriId, int confirmationCode){
//        return confirmationCodeRepo.save(new ConfirmationCode(useriId,confirmationCode));
//    }

}
