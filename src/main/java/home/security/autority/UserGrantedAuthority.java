package home.security.autority;

import org.springframework.security.core.GrantedAuthority;

public class UserGrantedAuthority implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return "user";
    }
}
