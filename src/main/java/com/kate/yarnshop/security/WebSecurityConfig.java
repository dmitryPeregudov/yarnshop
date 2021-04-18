package com.kate.yarnshop.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.kate.yarnshop.constants.Constants.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,
        securedEnabled = true)
@PropertySource("classpath:application.properties")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${shop.basic.auth.enabled}")
    private boolean basicAuthEnabled;
    private final JwtFilter jwtFilter;

    public WebSecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(SOCIAL_MEDIA_PATH,
                        REGISTER_PATH,
                        CONTACT_PATH,
                        PRODUCTS_PATH + EXTENDED_PATH,
                        PRODUCT_TYPES_PATH,
                        LOGIN_PATH)
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(SELLER_PATH + EXTENDED_PATH)
                .hasAuthority(ROLE_SELLER)
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        if (basicAuthEnabled) {
            http.httpBasic();
        }
    }
}
