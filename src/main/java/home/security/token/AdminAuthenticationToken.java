package home.security.token;

import home.security.autority.AdminGrantedAuthority;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

public class AdminAuthenticationToken extends AbstractAuthenticationToken{

    public AdminAuthenticationToken() {
        super(Collections.EMPTY_LIST);
    }

    public AdminAuthenticationToken(AdminGrantedAuthority authority){
        super(Collections.singletonList(authority));
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
