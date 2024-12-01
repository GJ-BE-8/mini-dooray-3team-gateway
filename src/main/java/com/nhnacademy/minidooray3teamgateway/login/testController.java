package com.nhnacademy.minidooray3teamgateway.login;

import com.nhnacademy.minidooray3teamgateway.login.feign.LoginServiceClient;
import java.io.UnsupportedEncodingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class testController {


    private final LoginServiceClient loginServiceClient;

    public testController(LoginServiceClient loginServiceClient) {
        this.loginServiceClient = loginServiceClient;
    }

    @GetMapping("/accounts/{email}")
    public AccountInfo test(@PathVariable String name) throws UnsupportedEncodingException {

        return loginServiceClient.getUserCredentials(name);
    }
}
