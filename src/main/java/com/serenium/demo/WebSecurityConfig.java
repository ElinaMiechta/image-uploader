package com.serenium.demo;

import com.serenium.demo.model.User;
import com.serenium.demo.repository.UserRepo;
import com.serenium.demo.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final UserRepo userRepo;


    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, UserRepo userRepo) {
        this.userDetailsService = userDetailsService;
        this.userRepo = userRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/upload").hasRole("ADMIN")
                .antMatchers("/gallery").hasRole("USER")
                .antMatchers("/test1").hasRole("USER")
                .and()
                .formLogin().permitAll()
                .and()
                .csrf().disable();


    }

    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        User user = new User("sara", passwordEncoder().encode("sara"), "ROLE_USER");
        User admin = new User("mark", passwordEncoder().encode("mark"), "ROLE_ADMIN");
        userRepo.save(user);
        userRepo.save(admin);


    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
