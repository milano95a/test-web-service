package home.exception;

/**
 * Created by AB on 29-Jul-17.
 */
public class QuestionConfigurationException extends Exception {

    String message;
    public QuestionConfigurationException(int i){
        message = "There is no enough questions with topic " + i;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
