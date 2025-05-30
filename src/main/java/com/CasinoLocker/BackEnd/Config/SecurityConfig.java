package com.CasinoLocker.BackEnd.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.CasinoLocker.BackEnd.Jwt.JwtAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                //Rutas publicas:
                                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
                                //.requestMatchers(new AntPathRequestMatcher("api/casilleros")).hasAnyAuthority("EMPLEADO", "ADMIN")
                                //.requestMatchers(new AntPathRequestMatcher("api/perchas")).hasAnyAuthority("EMPLEADO", "ADMIN")
                           


                                //Permitir registrar solo el administrador
                                //.requestMatchers(new AntPathRequestMatcher("/auth/register")).hasAnyAuthority("ADMIN") //Registro Administrador


                                //Todos pueden loguearse
                                //.requestMatchers(new AntPathRequestMatcher("/auth/login")).permitAll()
                                //.requestMatchers(new AntPathRequestMatcher("/api/usuarios/showProfile")).permitAll()
                                
                               


                                //.requestMatchers(new AntPathRequestMatcher("/auth/registerEmployee")).hasAuthority("ADMIN") //Autenticacion
                                //.requestMatchers(new AntPathRequestMatcher("/api/v1/usuario/modifyusuario")).hasAuthority("ADMIN")
                                //.requestMatchers(new AntPathRequestMatcher("/api/v1/usuario/deleteusuario")).hasAuthority("ADMIN")


                )

                .sessionManagement(sessionManager->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    /*
        @Bean
        CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedHeaders(List.of("Authorization", "Access-Control-Allow-Origin", "Content-Type",
                    "X-Requested-With", "accept", "Origin", " Access-Control-Request-Method",
                    "Access-Control-Request-Headers"));
            configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS"));
            configuration.setAllowedOrigins(List.of("http://localhost:8080"));
            configuration.setAllowCredentials(false);
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }*/
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS"));
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080","http://localhost:5173"));
        configuration.setAllowCredentials(true);// Si usas cookies o auth
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
