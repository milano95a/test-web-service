package home.security;

/**
 * Created by AB on 10-Aug-17.
 */

public class SecurityConstants {

    public static final int GOOGLE = 1;
    public static final int FACEBOOK = 2;

    public static final String URL_CHECK_TOKEN_GOOGLE2 = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=";
    public static final String URL_CHECK_TOKEN_GOOGLE = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    public static final String URL_CHECK_TOKEN_FACEBOOK = "https://graph.facebook.com/me?access_token=";

}
