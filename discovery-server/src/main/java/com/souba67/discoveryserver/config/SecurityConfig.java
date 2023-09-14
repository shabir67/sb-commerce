package com.souba67.discoveryserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Value("${app.eureka.username")
//    private String username;
//    @Value("${app.eureka.password}")
//    private String password;
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("username")
                .password("password")
                .roles("USER")
                .build();
        auth.inMemoryAuthentication()
                .withUser(user);

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //
                .authorizeRequests() //
                .antMatchers(HttpMethod.GET, "/eureka/**").authenticated() // eureka client
                .antMatchers(HttpMethod.POST, "/eureka/**").authenticated() // eureka client
                .antMatchers(HttpMethod.DELETE, "/eureka/**").authenticated() // eureka client
                .anyRequest().authenticated().and().httpBasic(); // dashboard authorization
    }
}
