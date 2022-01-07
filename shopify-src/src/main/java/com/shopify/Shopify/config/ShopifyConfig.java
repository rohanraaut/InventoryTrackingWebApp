package com.shopify.Shopify.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Generic Configuration class
 *
 * @author Rohan
 * @version 1.0.0
 * @since 7 Jan 2022
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class ShopifyConfig extends WebSecurityConfigurerAdapter {

    /**
     * Create bean for Model Mapper
     *
     * @return modelMapper Object
     * @author Rohan
     * @since 7 Jan 2022
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * Method to  giving access to specific url, specific resource to specific role
     *
     * @param http HttpSecurity
     * @throws Exception exception is thrown if user is not authenticated
     * @author Rohan
     * @since 7 Jan 2022
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().authorizeRequests()
                .antMatchers("/webjars/**", "/swagger-resources/**").permitAll()
                .antMatchers("/api/items/**")
                .permitAll().anyRequest().authenticated().and().csrf().disable();
        http.headers().frameOptions().sameOrigin();
    }
}
