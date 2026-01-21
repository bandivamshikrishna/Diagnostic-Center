package com.dc.config;

import com.dc.repository.UserAuthTokenRepository;
import com.dc.serviceImpl.UserAuthServiceImpl;
import com.dc.utils.JWTAuthenticationProvider;
import com.dc.utils.JWTRefreshFilter;
import com.dc.utils.JWTUtils;
import com.dc.utils.JWTValidationFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class UserAuthConfig {

    private static final List<String> publicURLs = List.of("/api/user/login",
            "/api/user/validate-token","/api/patient");

    public static List<String> getPublicURLs(){
        return publicURLs;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager,
                                                   JWTUtils jwtUtils, LogoutHandler logoutHandler,
                                                   UserAuthTokenRepository userAuthTokenRepository) throws Exception {

        JWTValidationFilter jwtValidationFilter = new JWTValidationFilter(authenticationManager);
        JWTRefreshFilter jwtRefreshFilter = new JWTRefreshFilter(authenticationManager,jwtUtils,userAuthTokenRepository);


        return  http
        .authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/api/user/logout").authenticated()
                        .requestMatchers(
                                 publicURLs.toArray(new String[0]))
                        .permitAll()
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtValidationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(jwtRefreshFilter, JWTValidationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/api/user/logout")
                        .permitAll(false)
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler(((request,
                                                response,
                                                authentication) -> {
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.getWriter().write("User Logged Out Successfully..");

                        })))
                        .build();
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
    public JWTAuthenticationProvider jwtAuthenticationProvider(JWTUtils jwtUtils,UserAuthServiceImpl userAuthService){
        return new JWTAuthenticationProvider(userAuthService,jwtUtils);
    }

}



