package com.dc.config;

import com.dc.serviceImpl.UserAuthServiceImpl;
import com.dc.utils.JWTAuthenticationProvider;
import com.dc.utils.JWTRefreshFilter;
import com.dc.utils.JWTUtils;
import com.dc.utils.JWTValidationFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class UserAuthConfig {

    private static final List<String> publicURLs = List.of("/api/user/login",
            "/api/user/validate-token");

    public static List<String> getPublicURLs(){
        return publicURLs;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,AuthenticationManager authenticationManager,JWTUtils jwtUtils) throws Exception {

        JWTValidationFilter jwtValidationFilter = new JWTValidationFilter(authenticationManager);
        JWTRefreshFilter jwtRefreshFilter = new JWTRefreshFilter(authenticationManager,jwtUtils);

        return  http
        .authorizeHttpRequests(
                auth -> auth.
                        requestMatchers(
                                 publicURLs.toArray(new String[0]))
                        .permitAll()
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtValidationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(jwtRefreshFilter, JWTValidationFilter.class).build();
    }



    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserAuthServiceImpl userAuthService, PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userAuthService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(@Lazy DaoAuthenticationProvider daoAuthenticationProvider, @Lazy JWTAuthenticationProvider jwtAuthenticationProvider){
        return new ProviderManager(Arrays.asList(daoAuthenticationProvider,jwtAuthenticationProvider));
    }

    @Bean
    public JWTAuthenticationProvider jwtAuthenticationProvider(JWTUtils jwtUtils, UserAuthServiceImpl userAuthService){
        return new JWTAuthenticationProvider(jwtUtils,userAuthService);
    }

}



