package com.nhnacademy.minidooray3teamgateway.taskApi.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectCreateRequestDTO {
    private String name;
    private String status;
}