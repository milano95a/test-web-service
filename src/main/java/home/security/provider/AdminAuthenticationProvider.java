package home.security.provider;

import home.security.autority.AdminGrantedAuthority;
import home.security.token.AdminAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AdminAuthenticationProvider implements AuthenticationProvider{

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AdminAuthenticationToken adminAuthenticationToken = new AdminAuthenticationToken(new AdminGrantedAuthority());
        return adminAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AdminAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
