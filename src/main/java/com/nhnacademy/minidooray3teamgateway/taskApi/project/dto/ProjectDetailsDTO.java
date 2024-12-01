package com.nhnacademy.minidooray3teamgateway.taskApi.project.dto;

import com.nhnacademy.minidooray3teamgateway.taskApi.milestone.dto.MilestoneSummaryDTO;
import com.nhnacademy.minidooray3teamgateway.taskApi.tag.dto.TagSummaryDTO;
import com.nhnacademy.minidooray3teamgateway.taskApi.task.dto.TaskSummaryDTO;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectDetailsDTO {
    private Long projectId;
    private String name;
    private String status;
    private LocalDateTime createdAt;
    private List<ProjectMemberSummaryDTO> projectMembers;
    private List<TaskSummaryDTO> tasks;
    private List<MilestoneSummaryDTO> milestones;
    private List<TagSummaryDTO> tags;
}
