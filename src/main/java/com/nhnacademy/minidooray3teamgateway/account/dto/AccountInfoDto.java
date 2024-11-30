package com.nhnacademy.minidooray3teamgateway.account.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.nhnacademy.minidooray3teamgateway.account.domain.Role;
import com.nhnacademy.minidooray3teamgateway.account.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfoDto {
    private Long accountId;
    private String username;
    private String email;

    @JsonSerialize(using = ToStringSerializer.class)
    private Role role;

    @JsonSerialize(using = ToStringSerializer.class)
    private Status status;

}