package home.security.autority;

import org.springframework.security.core.GrantedAuthority;

public class AdminGrantedAuthority implements GrantedAuthority{

    @Override
    public String getAuthority() {
        return "admin";
    }
}
