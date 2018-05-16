package home.security.domain;

public class ResetPassword {

    public String password;

    public ResetPassword(String password) {
        this.password = password;
    }

    public ResetPassword() {
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
