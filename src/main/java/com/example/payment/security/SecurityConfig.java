package com.example.payment.security;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf->csrf.disable())
                .cors(cors->{})
                .sessionManagement(sess->
                        sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(form->form.disable())
                .httpBasic(basic->basic.disable())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers(
                                "/api/auth/**",          // login register
                                "/v3/api-docs/**",       // swagger json
                                "/swagger-ui/**",        // swagger UI resources
                                "/swagger-ui.html"       // swagger entry
                        )
                        .permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((req, res, ex2) -> {
                            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            res.setContentType("application/json");
                            String msg="""
                            {
                                "status":401,
                                "error":"Unauthorized",
                                "message":"Please login first with valid credentials"
                            }
                            """;
                            res.getWriter().write(msg);
                        })
                );

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
