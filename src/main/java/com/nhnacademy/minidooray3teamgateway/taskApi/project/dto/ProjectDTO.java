package com.nhnacademy.minidooray3teamgateway.taskApi.project.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectDTO {
    private Long projectId;
    private String name;
    private String status;
    private LocalDateTime createdAt;
}