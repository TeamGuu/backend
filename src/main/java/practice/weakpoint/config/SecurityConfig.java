package practice.weakpoint.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
    private static final String[] AUTH_WHITELIST = {
            "/swagger-ui/**",
            "/api/**",
            "/api-docs/**"
    };

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorize -> authorize
                        .shouldFilterAllDispatcherTypes(false)
                        .requestMatchers(AUTH_WHITELIST)
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .csrf().disable().cors().disable()
                .build();
    }
}
