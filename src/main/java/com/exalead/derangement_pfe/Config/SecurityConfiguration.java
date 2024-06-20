    package com.exalead.derangement_pfe.Config;


    import jakarta.servlet.Filter;
    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.beans.factory.annotation.Qualifier;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.authentication.AuthenticationProvider;
    import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
    import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
    import org.springframework.security.web.authentication.logout.LogoutHandler;
    import org.springframework.web.context.request.WebRequest;
    import org.springframework.web.cors.CorsConfiguration;
    import org.springframework.web.cors.CorsConfigurationSource;
    import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
    import org.springframework.web.filter.CorsFilter;
    import org.springframework.web.servlet.HandlerExceptionResolver;

    import java.util.Arrays;
    import java.util.List;

    import static com.exalead.derangement_pfe.Entity.Permission.*;
    import static com.exalead.derangement_pfe.Entity.Role.ADMIN;
    import static com.exalead.derangement_pfe.Entity.Role.AOT;
    import static com.exalead.derangement_pfe.Entity.Role.ASC;
    import static org.springframework.http.HttpMethod.*;
    import static org.springframework.http.HttpMethod.DELETE;
    import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


    @Configuration
    @EnableWebSecurity
    @RequiredArgsConstructor
    @EnableMethodSecurity
    public class SecurityConfiguration {


        private static final String[] WHITE_LIST_URL = {"/api/v1/auth/**",
                "/v2/api-docs",
                "/v3/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui/**",
                "/webjars/**",
                "/swagger-ui.html",
                "/api/map/**" };
        private final JwtAuthenticationFilter jwtAuthFilter;
        private final AuthenticationProvider authenticationProvider;
        private final LogoutHandler logoutHandler;
        //private final GlobalExceptionHandler globalExceptionHandler;






        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(AbstractHttpConfigurer::disable)
                    .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Enable CORS
                    .authorizeHttpRequests(req ->
                            req.requestMatchers(WHITE_LIST_URL).permitAll()
                                    .requestMatchers("/api/v1/**").hasAnyRole(ADMIN.name(), AOT.name(), ASC.name(), "USER")
                                    //.requestMatchers(GET, "/api/v1/**").hasAnyAuthority(ADMIN_READ.name(), AOT_READ.name(), ASC_READ.name(), "USER")
                                    //.requestMatchers(POST, "/api/v1/**").hasAnyAuthority(ADMIN_CREATE.name(), AOT_CREATE.name(), ASC_CREATE.name(), "USER")
                                    //.requestMatchers(PUT, "/api/v1/**").hasAnyAuthority(ADMIN_UPDATE.name(), AOT_UPDATE.name(), ASC_UPDATE.name(), "USER")
                                    //.requestMatchers(DELETE, "/api/v1/**").hasAnyAuthority(ADMIN_DELETE.name(), AOT_DELETE.name(), ASC_DELETE.name(), "USER" )
                                    .anyRequest().authenticated()
                    )
                    .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                    .authenticationProvider(authenticationProvider)
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                    .logout(logout -> logout.logoutUrl("/api/v1/auth/logout")
                            .addLogoutHandler(logoutHandler)
                            .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()));

            return http.build();
        }



       /*@Bean
        CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();

            configuration.setAllowedOrigins(List.of("http://localhost:8443"));
            configuration.setAllowedMethods(List.of("GET","POST"));
            configuration.setAllowedHeaders(List.of("Authorization","Content-Type"));

            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

            source.registerCorsConfiguration("/**",configuration);

            return source;
        }*/

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowCredentials(true);
            configuration.setAllowedOrigins(List.of("http://localhost:64317"));
            configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
            configuration.setAllowedHeaders(List.of("Origin", "Content-Type", "Accept", "Authorization"));
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }





    }
