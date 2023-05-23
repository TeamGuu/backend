package teamguu.backend.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.CorsFilter;
import teamguu.backend.config.jwt.JwtAccessDeniedHandler;
import teamguu.backend.config.jwt.JwtAuthenticationEntryPoint;
import teamguu.backend.config.jwt.JwtProvider;
import teamguu.backend.config.jwt.JwtSecurityConfig;

import java.util.List;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtProvider jwtProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private static final String[] AUTH_WHITELIST = {
            "/swagger-ui/**",
            "/api-docs/**",
            "/api",
            "/api/auth/validate-duplicate",
            "/api/auth/sign-up",
            "/api/auth/sign-in"
    };

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .cors().configurationSource(request -> {
                    var cors = new CorsConfiguration();
                    cors.setAllowedOrigins(List.of("http://localhost:3000"));
                    cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
                    cors.setAllowedHeaders(List.of("*"));
                    return cors;
                })

                .and()
                .apply(new JwtSecurityConfig(jwtProvider))

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeHttpRequests(authorize -> authorize
                .shouldFilterAllDispatcherTypes(false)
                .antMatchers(AUTH_WHITELIST)
                .permitAll()
                .anyRequest()
                .authenticated())
                .build();
    }
}
