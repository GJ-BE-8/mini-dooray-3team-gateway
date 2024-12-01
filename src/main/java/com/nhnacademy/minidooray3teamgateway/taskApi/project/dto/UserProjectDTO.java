package com.nhnacademy.minidooray3teamgateway.taskApi.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProjectDTO {
    private Long projectId;
    private String name;
    private String status;
}