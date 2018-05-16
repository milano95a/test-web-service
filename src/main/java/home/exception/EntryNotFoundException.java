package home.exception;

/**
 * Created by AB on 31-Jul-17.
 */
public class EntryNotFoundException extends Exception {
    String entry;
    int id;

    public EntryNotFoundException(String entry, int id) {
        this.entry = entry;
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "(" + entry + ") with given id (" + id + ") not found";
    }
}
