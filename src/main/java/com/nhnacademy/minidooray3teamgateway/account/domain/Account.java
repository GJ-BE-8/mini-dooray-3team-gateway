package com.nhnacademy.minidooray3teamgateway.account.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {


    private Long accountId;

    private String username;

    private String email;

    private String password;

    private Status status; // status ENUM('ACTIVE', 'DORMANT', 'DELETED'),

    private Role role; // ADMIN, MEMBER

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Account(String username, String email, String password, Status status, Role role, LocalDateTime createdAt) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
        this.role = role;
        this.createdAt = createdAt;
    }
}
