package com.nhnacademy.minidooray3teamgateway.taskApi.comment.dto;

import com.nhnacademy.minidooray3teamgateway.taskApi.project.dto.ProjectMemberDTO;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponseDTO {
    private Long commentId;
    private Long taskId;
    private ProjectMemberDTO projectMember;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}