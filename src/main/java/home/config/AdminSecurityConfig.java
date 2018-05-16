package home.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(2)
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .requestMatchers().antMatchers("/admin/**")
                .and()
                .authorizeRequests()
                    .antMatchers("/admin/login/**")
                        .permitAll()
                    .antMatchers("/admin/**")
                        .authenticated()
                    .and()
                        .formLogin()
                        .loginPage("/admin/login")
                        .permitAll()
                    .and()
                        .logout()
                        .logoutUrl("/admin/logout")
                        .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("admin123").roles("ADMIN");
    }
}
