package com.nhnacademy.minidooray3teamgateway.config;

import com.nhnacademy.minidooray3teamgateway.login.CustomAuthenticationProvider;
import com.nhnacademy.minidooray3teamgateway.login.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomAuthenticationSuccessHandler customLoginSuccessHandler;
    private final CustomAuthenticationProvider customAuthenticationProvider;

    public SecurityConfig(CustomAuthenticationSuccessHandler customLoginSuccessHandler,
                          CustomAuthenticationProvider customAuthenticationProvider) {
        this.customLoginSuccessHandler = customLoginSuccessHandler;
        this.customAuthenticationProvider = customAuthenticationProvider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.authenticationProvider(customAuthenticationProvider);

        http.authorizeHttpRequests((authorizeRequests) ->
                authorizeRequests
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
        );

        http.formLogin((formLogin) ->
                formLogin.loginPage("/auth/login")
                        .usernameParameter("id")
                        .passwordParameter("password")
                        .successHandler(customLoginSuccessHandler)
                        .loginProcessingUrl("/login/process"));

        http.logout(logout -> logout.logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "SESSION")
                .permitAll());

        return http.build();
    }
}
