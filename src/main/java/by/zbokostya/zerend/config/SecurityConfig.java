package by.zbokostya.zerend.config;

import by.zbokostya.zerend.security.api.ApiKeyFilter;
import by.zbokostya.zerend.security.jwt.JWTFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JWTFilter jwtFilter;
    private final ApiKeyFilter apiKeyFilter;

    public SecurityConfig(JWTFilter jwtFilter, ApiKeyFilter apiKeyFilter) {
        this.jwtFilter = jwtFilter;
        this.apiKeyFilter = apiKeyFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(apiKeyFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests((auth) -> auth
                        .antMatchers("/api/register").permitAll()
                        .antMatchers("/api/authenticate").permitAll()
                        .antMatchers("/api/verify**").permitAll()
                        .antMatchers("/user/**").authenticated()
                        .antMatchers("/user/project/**/apikey").permitAll()
                        .antMatchers("/apikey/project/**")
                        .hasAnyAuthority("ROLE_VIEWER")
                        .antMatchers("/apikey/project/**/ability/**")
                        .hasAnyAuthority("ROLE_VIEWER"))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}
