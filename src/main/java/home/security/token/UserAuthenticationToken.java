package home.security.token;

import home.security.autority.UserGrantedAuthority;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

public class UserAuthenticationToken extends AbstractAuthenticationToken{

    String token;

    public UserAuthenticationToken(UserGrantedAuthority authority){
        super(Collections.singletonList(authority));
    }

    public UserAuthenticationToken(String token){
        super(Collections.EMPTY_LIST);
        this.token = token;
    }

    public UserAuthenticationToken(){
        super(Collections.EMPTY_LIST);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public void setDetails(Object details) {
        super.setDetails(details);
    }
}
