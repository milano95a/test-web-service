package home.security.domain;

public class ConfirmationRequest {
    private String email;
    private int confirmationCode;

    public ConfirmationRequest() {}

    public ConfirmationRequest(String email, int confirmationCode) {
        this.email = email;
        this.confirmationCode = confirmationCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getConfirmationCode() {
        return confirmationCode;
    }

    public String getConfirmaitonCodeStirng(){
        return String.valueOf(confirmationCode);
    }

    public void setConfirmationCode(int confirmationCode) {
        this.confirmationCode = confirmationCode;
    }
}
