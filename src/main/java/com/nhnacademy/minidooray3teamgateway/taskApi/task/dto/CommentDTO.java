package com.nhnacademy.minidooray3teamgateway.taskApi.task.dto;

import com.nhnacademy.minidooray3teamgateway.taskApi.project.dto.ProjectMemberDTO;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CommentDTO {
    private Long commentId;
    private ProjectMemberDTO projectMember;
    private String content;
    private LocalDateTime createdAt;
}
