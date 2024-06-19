package com.github.d3.security.config;

import com.github.d3.filter.CommonFilter;
import com.github.d3.security.handler.CustomAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SecurityConfig {

    /**
     * 通用filter
     */
    private final CommonFilter commonFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/v3/api-docs").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(
                        exceptions ->
                                exceptions.accessDeniedHandler(new CustomAccessDeniedHandler())
                )
                // 关闭跨域防护
                .csrf(AbstractHttpConfigurer::disable)
        ;
        // common filter
        http.addFilterBefore(commonFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}