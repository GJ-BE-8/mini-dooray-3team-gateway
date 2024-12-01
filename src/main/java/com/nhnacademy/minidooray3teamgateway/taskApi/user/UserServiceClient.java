package com.nhnacademy.minidooray3teamgateway.taskApi.user;


import com.nhnacademy.minidooray3teamgateway.taskApi.user.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "users", url = "http://localhost:8082")
public interface UserServiceClient {
    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO);
}
