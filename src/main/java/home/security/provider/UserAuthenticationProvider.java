package home.security.provider;

import home.entity.User;
import home.repo.IUserRepo;
import home.security.autority.UserGrantedAuthority;
import home.security.domain.Token;
import home.security.repository.ITokenRepo;
import home.security.token.UserAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    ITokenRepo tokenRepo;

    @Autowired
    IUserRepo userRepo;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String token = authentication.getCredentials().toString();

        Token dbToken = null;

        if(token != null){
            dbToken = tokenRepo.findOne(token);
        }

        if(dbToken != null){
            UserAuthenticationToken authenticationToken = new UserAuthenticationToken(new UserGrantedAuthority());
            User user = userRepo.findOne(dbToken.getUserId());
            authenticationToken.setDetails(user);
            authentication.setAuthenticated(true);
            return authenticationToken;
        }

        UserAuthenticationToken unauthorizedToken = new UserAuthenticationToken();

        return unauthorizedToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UserAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
