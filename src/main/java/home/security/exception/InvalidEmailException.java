package home.security.exception;

public class InvalidEmailException extends Exception{
    String email;

    public InvalidEmailException(String email) {
        this.email = email;
    }

    @Override
    public String getMessage() {
        return "Invalid token: " + email;
    }
}
