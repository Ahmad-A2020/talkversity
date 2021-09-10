package com.example.talkversity.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // to configure the authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());

    }

    // to configure  the authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/admin").hasAnyRole("ADMIN","MAIN_ADMIN")
//                .antMatchers("/user").hasAnyRole("STUDENT","ADMIN","MAIN_ADMIN")
//                .antMatchers("/","/login","/*.css","/*.PNG","/*.png","/*.js").permitAll()
                .anyRequest().authenticated()//any other pages you have to be authenticated
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/login")
                .permitAll()
                ;
//        http.csrf().disable();
//        http.headers().frameOptions().disable();

    }
}
