package com.assignment.springboot.mongo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


 
@Configuration
public class CustomerSecurity extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception 
    {
        http
         .csrf().disable()
         .authorizeRequests().anyRequest().authenticated()
         .and()
         .httpBasic();
        http
        .csrf().disable()
        .authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }
    
    
  
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)         throws Exception 
    {
        auth.inMemoryAuthentication()
          
       .withUser("technical").password("{noop}Assessment").roles("USER");
            
    }
    
   
    
  
}