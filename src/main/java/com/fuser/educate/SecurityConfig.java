package com.fuser.educate;

import com.fuser.educate.service.MainUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MainUserService mainUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/static/favicon.ico")
                        .permitAll()
                    .antMatchers("/learn/**")
                        .authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/learn/profile")
                    .failureUrl("/login?badCredentials")
                .and()
                .logout()
                    .logoutUrl("/perform_logout")
                    .logoutSuccessUrl("/login?logout=true")
                    .deleteCookies("JSESSIONID", "remember-me")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                .and()
                .rememberMe();
    }

    @Autowired
    public void setApplicationContext(ApplicationContext context) {
        super.setApplicationContext(context);
        AuthenticationManagerBuilder globalAuthBuilder = context
                .getBean(AuthenticationManagerBuilder.class);
        try {
            globalAuthBuilder.userDetailsService(mainUserService);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
