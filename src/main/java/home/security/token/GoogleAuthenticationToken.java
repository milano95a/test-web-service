package home.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by AB on 10-Aug-17.
 */
public class GoogleAuthenticationToken extends AbstractAuthenticationToken {

    public GoogleAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getDetails() {
        return super.getDetails();
    }

    @Override
    public void setDetails(Object details) {
        super.setDetails(details);
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
