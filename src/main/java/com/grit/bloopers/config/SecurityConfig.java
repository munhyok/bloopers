package com.grit.bloopers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;


/*
hasRole() 혹은 hasAnyRole()
특정한 역할을 가지는 사용자만 접근 가능
ADMIN, USER 등

hasAuthority() 혹은 hasAnyAuthority()
특정한 권한을 가지는 사용자만 접근 가능
ROLE_ADMIN, ROLE_USER 등

permitAll() -> 전체 접근 허용

denyAll() -> 전체 접근 제한

rememberMe() ->리멤버 기능으로 로그인한 사용자만 접근 가능

anonymous() -> 인증되지 않은 사용자만 접근 가능

authenticated() -> 인증된 사용자만 접근 가능
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String[] allowedUrls = {
            "/","/register","/portfolios",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer:: disable)

                .authorizeHttpRequests((auth) -> auth
                        //.requestMatchers(allowedUrls).permitAll()
                        //.anyRequest().authenticated()
                        .anyRequest().permitAll()

                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .maximumSessions(1)
                );

                //.logout(logout -> logout.deleteCookies("JSESSIONID").invalidateHttpSession(true)

                //);





        return http.build();
    }




    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }





}
