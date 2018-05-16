package home.security;

import home.security.token.UserAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, javax.servlet.FilterChain filterChain) throws ServletException, IOException {

        String token = httpServletRequest.getHeader("Authorization");
        if (token != null) {
            SecurityContextHolder.getContext().setAuthentication(new UserAuthenticationToken(token));
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
