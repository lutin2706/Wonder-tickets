package be.formation.spring.labo.config;

import be.formation.spring.labo.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    public WebSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/user/**")
                .hasRole("CUSTOMER")
                .antMatchers("/admin/employee/**")
                .hasRole("ADMIN")
                .antMatchers("/admin/**")
                .hasAnyRole("EMPLOYEE", "ADMIN")
                .antMatchers("/**")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/process")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and().csrf().ignoringAntMatchers("/h2/**")
                .and().headers().frameOptions().disable();
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userService;
    }
}
