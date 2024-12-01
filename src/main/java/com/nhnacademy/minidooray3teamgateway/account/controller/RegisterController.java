package com.nhnacademy.minidooray3teamgateway.account.controller;


import com.nhnacademy.minidooray3teamgateway.account.domain.Account;
import com.nhnacademy.minidooray3teamgateway.account.dto.AccountModifyDto;
import com.nhnacademy.minidooray3teamgateway.account.dto.AccountRegisterDto;
import com.nhnacademy.minidooray3teamgateway.account.feign.RegisterServiceClient;
import com.nhnacademy.minidooray3teamgateway.task.user.UserServiceClient;
import com.nhnacademy.minidooray3teamgateway.task.user.dto.UserDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/accounts")
public class RegisterController {

    private final RegisterServiceClient registerServiceClient;

    private final UserServiceClient userServiceClient;

    public RegisterController(RegisterServiceClient registerServiceClient, UserServiceClient userServiceClient) {
        this.registerServiceClient = registerServiceClient;
        this.userServiceClient = userServiceClient;
    }

    @GetMapping
    public ResponseEntity<String> getAccounts() {
        return ResponseEntity.ok("GET 요청이 처리되었습니다.");
    }

    //회원가입
    @Transactional
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody @Valid AccountRegisterDto accountDto) {
        ResponseEntity<String> response = registerServiceClient.register(accountDto);
        userServiceClient.createUser(new UserDTO(accountDto.getUsername(), accountDto.getUsername(), accountDto.getEmail()));
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
    //상태수정
    @PostMapping(value = "/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RedirectView updateAccount(@PathVariable Long accountId, @RequestBody @Valid
    AccountModifyDto accountModifyDto) {
        registerServiceClient.updateAccount(accountId, accountModifyDto);
        return new RedirectView("/");
    }


}

