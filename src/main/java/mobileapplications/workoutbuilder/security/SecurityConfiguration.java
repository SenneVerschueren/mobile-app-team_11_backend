package mobileapplications.workoutbuilder.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
          .cors(cors -> cors.configurationSource(request -> new org.springframework.web.cors.CorsConfiguration().applyPermitDefaultValues()))
          .authorizeHttpRequests(req -> req
              .requestMatchers("/api/auth/**").permitAll() // Allow public access to authentication endpoints
              .anyRequest().authenticated() // Require authentication for all other requests
          )
          .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // Add JWT filter

        return http.build();
    }
}