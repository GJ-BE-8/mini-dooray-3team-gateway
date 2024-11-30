package com.nhnacademy.minidooray3teamgateway.project;

import com.nhnacademy.minidooray3teamgateway.project.dto.ProjectCreateRequestDTO;
import com.nhnacademy.minidooray3teamgateway.project.dto.ProjectDTO;
import com.nhnacademy.minidooray3teamgateway.project.feign.ProjectServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProjectGatewayService{

   private final ProjectServiceClient projectServiceClient;

    public ProjectGatewayService(ProjectServiceClient projectServiceClient) {
        this.projectServiceClient = projectServiceClient;
    }


    public ProjectDTO createProject(String userId, ProjectCreateRequestDTO requestDTO) {
        ResponseEntity<ProjectDTO> response = projectServiceClient.createProject(userId, requestDTO);

        return response.getBody();
    }
}

