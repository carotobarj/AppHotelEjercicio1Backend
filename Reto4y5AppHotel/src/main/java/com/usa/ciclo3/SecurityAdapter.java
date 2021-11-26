/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usa.ciclo3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Carotobarj
 */
@RestController
public class SecurityAdapter extends WebSecurityConfigurerAdapter{
    
    @Autowired
    public void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests(a -> a
                        .antMatchers("/", "/error", "/webjars/**",
                                "/api/**").permitAll()
                        .anyRequest().authenticated()
                ).exceptionHandling(e -> e
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                ).oauth2Login().defaultSuccessUrl("/", true);

        http.cors().and().csrf().disable();
    }
}
