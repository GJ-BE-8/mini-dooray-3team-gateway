package com.nhnacademy.minidooray3teamgateway.project.feign;

import com.nhnacademy.minidooray3teamgateway.project.dto.ProjectCreateRequestDTO;
import com.nhnacademy.minidooray3teamgateway.project.dto.ProjectDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "project", url = "http://localhost:8082")
public interface ProjectServiceClient {
    @PostMapping("/projects")
    ResponseEntity<ProjectDTO> createProject(@RequestHeader("X-User-Id") String userId,
                                             @RequestBody ProjectCreateRequestDTO requestDTO);
}