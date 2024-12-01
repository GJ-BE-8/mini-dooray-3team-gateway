package com.nhnacademy.minidooray3teamgateway.login.feign;


import com.nhnacademy.minidooray3teamgateway.login.AccountInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (name = "login", url = "http://localhost:8081")
public interface LoginServiceClient {
    @GetMapping("/accounts/{name}")
    public AccountInfo getUserCredentials(@PathVariable String name);
}
