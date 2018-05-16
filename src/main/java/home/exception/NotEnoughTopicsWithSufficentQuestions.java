package home.exception;

public class NotEnoughTopicsWithSufficentQuestions extends Exception{

    private int subjectId;

    public NotEnoughTopicsWithSufficentQuestions(int subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String getMessage() {
        return "Not enough topics with sufficient questions in subject " + subjectId;
    }
}
