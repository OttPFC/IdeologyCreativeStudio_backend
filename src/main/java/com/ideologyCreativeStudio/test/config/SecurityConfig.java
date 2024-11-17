package com.ideologyCreativeStudio.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    public PasswordEncoder stdPasswordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public AuthTokenFilter authenticationJwtToken() {
        return new AuthTokenFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       PasswordEncoder passwordEncoder,
                                                       UserDetailsService userDetailsService) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/user/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/projects/*").authenticated()
                        .requestMatchers(HttpMethod.GET, "/clients/*").authenticated()
                        // ------------------------------------------------------------------------
                        .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user").authenticated()
                        .requestMatchers(HttpMethod.POST, "/projects").authenticated()
                        .requestMatchers(HttpMethod.POST, "/clients").authenticated()
                        // ------------------------------------------------------------------------
                        .requestMatchers(HttpMethod.PUT, "/user/{id}").authenticated()
                        //ADMIN
                        .requestMatchers(HttpMethod.GET, "/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/**").hasAuthority("ADMIN")

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authenticationJwtToken(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.setAllowedHeaders(Arrays.asList(
                HttpHeaders.AUTHORIZATION,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT));
        config.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.PATCH.name(),
                HttpMethod.OPTIONS.name(),
                HttpMethod.HEAD.name(),
                HttpMethod.TRACE.name(),
                HttpMethod.DELETE.name()));
        config.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", config);
        return source;
    }

//    @Value("${app.cors.allowedOrigins}")
//    private String[] allowedOrigins;
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource2() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList(allowedOrigins));
//        configuration.setAllowedHeaders(Arrays.asList(
//                HttpHeaders.AUTHORIZATION,
//                HttpHeaders.CONTENT_TYPE,
//                HttpHeaders.ACCEPT
//        ));
//        configuration.setAllowedMethods(Arrays.asList(
//                HttpMethod.GET.name(),
//                HttpMethod.POST.name(),
//                HttpMethod.PUT.name(),
//                HttpMethod.DELETE.name()
//        ));
//        configuration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
}
