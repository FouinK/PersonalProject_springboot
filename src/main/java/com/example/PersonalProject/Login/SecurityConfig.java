package com.example.PersonalProject.Login;

import com.example.PersonalProject.Filter.SessionAuthenticationFilter;
import com.example.PersonalProject.Filter.SessionAuthorizationFilter;
import com.example.PersonalProject.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SessionManager sessionManager;
    private final CorsFilter corsFilter;
    private final UserRepository userRepository;
    private final LogoutHandler logoutHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter)
                .formLogin().disable()
                .httpBasic().disable()
                .addFilter(new SessionAuthenticationFilter(authenticationManager(), sessionManager))
                .authorizeRequests()
                .antMatchers("/api/logout")
                .access("hasRole('ROLE_USER')or hasRole('USER')or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
//                .antMatchers("/oauth2/**").permitAll()      //이거 빼고는 authenticationManager
                .antMatchers("/api/v1/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();
        http
                .addFilterBefore(
                        new SessionAuthorizationFilter(authenticationManager(), sessionManager, userRepository),
                        UsernamePasswordAuthenticationFilter.class
                );
        http.logout()
                .logoutUrl("/api/logout")
                .deleteCookies("mySessionId")
                .invalidateHttpSession(true)
                .logoutSuccessHandler(logoutHandler);
    }
}
