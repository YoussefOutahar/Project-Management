package com.yorastd.projectmanagement.Security.config;

import com.yorastd.projectmanagement.Models.Role;
import com.yorastd.projectmanagement.Security.filters.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static com.yorastd.projectmanagement.Models.Permission.*;
import static com.yorastd.projectmanagement.Models.Role.ADMIN;
import static com.yorastd.projectmanagement.Models.Role.MANAGER;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final RequestMatcher[] WHITE_LIST_URL = {
            antMatcher("/api/v1/auth/**"),
            antMatcher("/v2/api-docs"),
            antMatcher("/v3/api-docs"),
            antMatcher("/v3/api-docs/**"),
            antMatcher("/swagger-resources"),
            antMatcher("/swagger-resources/**"),
            antMatcher("/configuration/ui"),
            antMatcher("/configuration/security"),
            antMatcher("/swagger-ui/**"),
            antMatcher("/webjars/**"),
            antMatcher("/swagger-ui.html"),
    };
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers(antMatcher("/api/v1/management/**")).hasAnyRole(ADMIN.name(), MANAGER.name())
                                // add more request matchers here
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )
        ;

        return http.build();
    }
}