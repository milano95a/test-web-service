package home.model.view;

import home.entity.User;
import home.security.domain.LoginType;
import java.util.Date;

/**
 * Created by AB on 23-Aug-17.
 */

public class UserView {

    String id;
    String username;
    String password;
    Date createDate;
    LoginType loginType;
    Integer score;

    public UserView(User user) {
        this.id = user.getUserId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.createDate = user.getCreateDate();
        this.loginType = user.getLoginType();
        this.score = user.getScore();
    }

    public UserView() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}