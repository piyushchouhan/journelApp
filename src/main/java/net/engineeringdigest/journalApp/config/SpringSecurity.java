package net.engineeringdigest.journalApp.config;

import org.springframework.boot.web.server.Http2;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfiguration {

    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/api/user").permitAll()
                .antMatchers("/api/user/**").permitAll()
                .antMatchers("/api/journal").permitAll()
                .antMatchers("/api/journal/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }
}
