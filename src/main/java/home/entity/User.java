package home.entity;

import home.security.domain.LoginType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User {

    @Id
    @Column(name = "USER_ID")
    String userId;
    String username;
    String password;
    Date createDate;
    Date lastSeen;
    LoginType loginType;
    Integer score;

    public User() {
    }

    public User(String userId, String username, LoginType loginType) {
        this.userId = userId;
        this.username = username;
        this.loginType = loginType;
    }

    public User(String userId, String username, LoginType loginType, String password) {
        this.userId = userId;
        this.username = username;
        this.loginType = loginType;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void incrementScore(Integer score){
        this.score += score;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }
}
