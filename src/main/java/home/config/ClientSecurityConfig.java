package home.config;

import home.security.AuthenticationFilter;
import home.security.provider.AdminAuthenticationProvider;
import home.security.provider.UserAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@Order(1)
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ClientSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserAuthenticationProvider userAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(userAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .requestMatchers().antMatchers("/api/**")
                .and()
                .authenticationProvider(userAuthenticationProvider)
                .addFilterBefore(new AuthenticationFilter(), BasicAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/**")
                .permitAll()
                .antMatchers("/api/authgoogle",
                        "/api/authfacebook",
                        "/api/registeremail",
                        "/api/confirmtoken",
                        "/api/authemail",
                        "/api/forgot")
                .permitAll()
//                .anyRequest().permitAll()
                .antMatchers("/api/**").hasAuthority("user")
        ;
    }
}
