package home.exception;

/**
 * Created by AB on 30-Jul-17.
 */

public class NotEnoughQuestionsException extends Exception{

    String message;

    public NotEnoughQuestionsException(String title, int id){
        message =  "No questions with the given " + title + " id = " + id;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
