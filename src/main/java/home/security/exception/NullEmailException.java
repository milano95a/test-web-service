package home.security.exception;

public class NullEmailException extends Exception{

    public NullEmailException(){}

    @Override
    public String getMessage() {
        return "email is null";
    }
}
