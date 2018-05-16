package home.entity;

import javax.persistence.*;

/**
 * Created by AB on 07-Sep-17.
 */
@Entity
public class Ex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String params;

    String headStackTrace;

//    String stackTrace;

    String message;

    String cause;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getHeadStackTrace() {
        return headStackTrace;
    }

    public void setHeadStackTrace(String headStackTrace) {
        this.headStackTrace = headStackTrace;
    }

//    public String getStackTrace() {
//        return stackTrace;
//    }
//
//    public void setStackTrace(String stackTrace) {
//        this.stackTrace = stackTrace;
//    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
