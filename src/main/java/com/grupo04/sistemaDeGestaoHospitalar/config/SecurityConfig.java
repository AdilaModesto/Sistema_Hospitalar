package com.grupo04.sistemaDeGestaoHospitalar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Se você já tem um filtro JWT, injete aqui
    // @Autowired
    // private JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll() // 👈 Todos os endpoints públicos
                )
                .formLogin(form -> form.disable()) // Desativa redirecionamento para /login
                .httpBasic(basic -> basic.disable()); // Desativa popup de autenticação básica

        return http.build();
    }
}