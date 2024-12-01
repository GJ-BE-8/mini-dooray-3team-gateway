package com.nhnacademy.minidooray3teamgateway.account.feign;

import com.nhnacademy.minidooray3teamgateway.account.domain.Account;
import com.nhnacademy.minidooray3teamgateway.account.dto.AccountInfoDto;
import com.nhnacademy.minidooray3teamgateway.account.dto.AccountModifyDto;
import com.nhnacademy.minidooray3teamgateway.account.dto.AccountRegisterDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "register", url = "http://localhost:8081")
public interface RegisterServiceClient {
    @PostMapping(value = "/accounts", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody @Valid AccountRegisterDto accountDto);


    @PostMapping(value = "/accounts/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> updateAccount(@PathVariable Long accountId, @RequestBody  @Valid
    AccountModifyDto accountModifyDto);

    @PostMapping("/accounts/info/delete/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountId);

    @GetMapping("/getId/{name}")
    public Long getId(@PathVariable String name);

    @GetMapping("/accounts/info/{accountId}")
    public ResponseEntity<AccountInfoDto> showAccountInfo(@PathVariable Long accountId);

}
