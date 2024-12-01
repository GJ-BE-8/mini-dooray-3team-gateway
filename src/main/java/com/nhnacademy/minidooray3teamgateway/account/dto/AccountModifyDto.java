package com.nhnacademy.minidooray3teamgateway.account.dto;

import com.nhnacademy.minidooray3teamgateway.account.domain.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountModifyDto {
    //private String username;
    private Status status;
}