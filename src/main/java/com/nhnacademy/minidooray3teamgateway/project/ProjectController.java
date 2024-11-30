package com.nhnacademy.minidooray3teamgateway.project;

import com.nhnacademy.minidooray3teamgateway.project.dto.ProjectCreateRequestDTO;
import com.nhnacademy.minidooray3teamgateway.project.dto.ProjectDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
public class ProjectController {

   private final ProjectGatewayService projectGatewayService;

    public ProjectController(ProjectGatewayService projectGatewayService) {
        this.projectGatewayService = projectGatewayService;
    }


    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(HttpSession session,
                                                    @RequestBody ProjectCreateRequestDTO requestDTO) {
        String userId = (String) session.getAttribute("loginName");

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        ProjectDTO project = projectGatewayService.createProject(userId, requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }
}

