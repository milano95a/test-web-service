package home.security.domain;

public class RegisterEmail {
    private String email;

    public RegisterEmail(String email) {
        this.email = email;
    }

    public RegisterEmail() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
