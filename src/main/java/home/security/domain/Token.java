package home.security.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by AB on 10-Aug-17.
 */
@Entity
public class Token {

    @Id
    String token;

    String userId;

    public Token() {
    }

    public Token(String token, String userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
