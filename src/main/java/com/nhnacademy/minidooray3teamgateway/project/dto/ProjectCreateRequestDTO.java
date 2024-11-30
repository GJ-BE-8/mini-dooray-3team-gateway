package com.nhnacademy.minidooray3teamgateway.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectCreateRequestDTO {
    private String name;
    private String status;
}
