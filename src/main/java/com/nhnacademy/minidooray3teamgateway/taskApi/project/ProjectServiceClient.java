package com.nhnacademy.minidooray3teamgateway.taskApi.project;


import com.nhnacademy.minidooray3teamgateway.taskApi.project.dto.ProjectCreateRequestDTO;
import com.nhnacademy.minidooray3teamgateway.taskApi.project.dto.ProjectDTO;
import com.nhnacademy.minidooray3teamgateway.taskApi.project.dto.ProjectDetailsDTO;
import com.nhnacademy.minidooray3teamgateway.taskApi.project.dto.ProjectMemberDTO;
import com.nhnacademy.minidooray3teamgateway.taskApi.project.dto.ProjectMemberSummaryDTO;
import com.nhnacademy.minidooray3teamgateway.taskApi.project.dto.UserProjectDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "project", url = "http://localhost:8082")
public interface ProjectServiceClient {

    @PostMapping("/projects")
    public ResponseEntity<ProjectDTO> createProject(@RequestHeader("X-User-Id") String userId,
                                                    @RequestBody ProjectCreateRequestDTO requestDTO);


    @GetMapping("/projects/{id}")
    public ResponseEntity<ProjectDetailsDTO> getProjectDetails(@RequestHeader("X-User-Id") String userId,
                                                               @PathVariable Long id);

    @GetMapping("/users/{userId}/projects")
    public ResponseEntity<List<UserProjectDTO>> getUserProjects(@RequestHeader("X-User-Id") String userId);

    @PostMapping(value = "/projects/{id}/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteProject(@RequestHeader("X-User-Id") String userId, @PathVariable Long id);

    @PostMapping(value = "/projects/{id}/members", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectMemberDTO> addMemberToProject(@RequestHeader("X-User-Id") String userId,
                                                               @PathVariable Long id,
                                                               @RequestBody ProjectMemberSummaryDTO memberDTO);

    @PostMapping(value = "/projects/{id}/members/{memberUserId}/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> removeMemberFromProject(@RequestHeader("X-User-Id") String userId,
                                                        @PathVariable Long id,
                                                        @PathVariable String memberUserId);
}
