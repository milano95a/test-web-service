package home.security.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by AB on 11-Aug-17.
 */
@Entity
public class ConfirmationCode {
    @Id
    private String userId;

    private int confirmationCode;

    public ConfirmationCode() {
    }

    public ConfirmationCode(String userId, int confirmationCode) {
        this.userId = userId;
        this.confirmationCode = confirmationCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(int confirmationCode) {
        this.confirmationCode = confirmationCode;
    }
}
