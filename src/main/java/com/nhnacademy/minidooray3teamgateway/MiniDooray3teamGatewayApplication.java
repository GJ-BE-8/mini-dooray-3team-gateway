package com.nhnacademy.minidooray3teamgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MiniDooray3teamGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniDooray3teamGatewayApplication.class, args);
    }

}
