package com.nhnacademy.minidooray3teamgateway.taskApi.project;


import com.nhnacademy.minidooray3teamgateway.taskApi.project.dto.ProjectCreateRequestDTO;
import com.nhnacademy.minidooray3teamgateway.taskApi.project.dto.ProjectDetailsDTO;
import com.nhnacademy.minidooray3teamgateway.taskApi.project.dto.ProjectMemberSummaryDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ProjectGatewayController {

    private final ProjectServiceClient projectServiceClient;

    public ProjectGatewayController(ProjectServiceClient projectServiceClient) {
        this.projectServiceClient = projectServiceClient;
    }

    @GetMapping("/projects/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("create-project");
        return modelAndView;
    }

    @PostMapping("/projects")
    public RedirectView createProject(
            @RequestBody ProjectCreateRequestDTO requestDTO, HttpSession session) {
        Object loginName = session.getAttribute("loginName");
        if (loginName == null) {
            throw new IllegalStateException("로그인 정보가 없습니다. 세션이 만료되었을 수 있습니다.");
        }

        String userId = loginName.toString();
        projectServiceClient.createProject(userId, requestDTO);

        return new RedirectView("/");
    }

    @GetMapping("/projects/{id}")
    public ModelAndView getProject(@PathVariable("id") Long id, HttpSession session, Model model) {
        Object loginName = session.getAttribute("loginName");
        if (loginName == null) {
            throw new IllegalStateException("로그인하세요");
        }
        String userId = loginName.toString();
        ResponseEntity<ProjectDetailsDTO> projectDetails = projectServiceClient.getProjectDetails(userId, id);
        ProjectDetailsDTO body = projectDetails.getBody();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("loginName", userId);
        modelAndView.addObject("project", body);
        modelAndView.setViewName("project-detail");
        return modelAndView;
    }


    @GetMapping("/projects/{id}/members/new")
    public ModelAndView addProject(@PathVariable("id") Long id, HttpSession session) {
        Object loginName = session.getAttribute("loginName");
        if (loginName == null) {
            return new ModelAndView("redirect:/login");
        }

        ModelAndView modelAndView = new ModelAndView("add-project-member");
        modelAndView.addObject("projectId", id);
        return modelAndView;
    }


    @PostMapping(value = "/projects/{id}/members", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RedirectView addMember(@PathVariable("id") Long id,
                                  @RequestBody ProjectMemberSummaryDTO projectMemberSummaryDTO,
                                  HttpSession session) {
        Object loginName = session.getAttribute("loginName");
        if (loginName == null) {
            return new RedirectView("/");
        }
        String name = loginName.toString();
        projectServiceClient.addMemberToProject(name, id, projectMemberSummaryDTO);
        return new RedirectView("/projects/" + id);
    }

    @PostMapping(value = "projects/{id}/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteProject(@RequestHeader("X-User-Id") String userId, @PathVariable Long id) {

        projectServiceClient.deleteProject(userId, id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/projects/{id}/members/{memberUserId}/remove", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> removeMemberFromProject(@RequestHeader("X-User-Id") String userId,
                                                        @PathVariable Long id,
                                                        @PathVariable String memberUserId) {

        projectServiceClient.removeMemberFromProject(userId, id, memberUserId);
        return ResponseEntity.noContent().build();
    }

}




