package home.exception;

public class NotEnoughTopicException extends Exception{
    private int id;

    public NotEnoughTopicException(int id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Not enough topics with subject id: " + id;
    }
}
