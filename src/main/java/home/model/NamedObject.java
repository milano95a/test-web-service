package home.model;

/**
 * Created by AB on 30-Jul-17.
 */

public class NamedObject<T> {
    public final String name;
    public final T object;

    public NamedObject(T object,String name) {
        this.name = name;
        this.object = object;
    }

    public String getName() {
        return name;
    }

    public T getObject() {
        return object;
    }
}
