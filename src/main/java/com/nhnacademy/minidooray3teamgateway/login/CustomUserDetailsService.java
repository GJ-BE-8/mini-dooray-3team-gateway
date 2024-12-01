package com.nhnacademy.minidooray3teamgateway.login;

import com.nhnacademy.minidooray3teamgateway.login.feign.LoginServiceClient;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final LoginServiceClient loginServiceClient;

    public CustomUserDetailsService(LoginServiceClient loginServiceClient) {
        this.loginServiceClient = loginServiceClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountInfo accountInfo = loginServiceClient.getUserCredentials(username);

        if (accountInfo == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return User.builder()
                .username(accountInfo.getUsername())
                .password(accountInfo.getPassword())
                .roles("USER")
                .build();
    }
}

