package home.security.domain;

public class TokenRequest {
    private String token;

    public TokenRequest(String token) {
        this.token = token;
    }

    public TokenRequest() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
