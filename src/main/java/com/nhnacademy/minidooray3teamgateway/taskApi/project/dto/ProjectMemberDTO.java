package com.nhnacademy.minidooray3teamgateway.taskApi.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMemberDTO {
    private Long projectMemberId;
    private String userId;
    private String role;
}