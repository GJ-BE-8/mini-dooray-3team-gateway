package com.nhnacademy.minidooray3teamgateway.taskApi.comment;

import com.nhnacademy.minidooray3teamgateway.taskApi.comment.dto.CommentRequestDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.MediaType;
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
public class CommentGatewayController {

    private final CommentServiceClient commentServiceClient;

    public CommentGatewayController(CommentServiceClient commentServiceClient) {
        this.commentServiceClient = commentServiceClient;
    }

    @GetMapping("/projects/{projectId}/tasks/{taskId}/comments")
    public ModelAndView getComments(@PathVariable("projectId") Long projectId,
                                    @PathVariable Long taskId, HttpSession session) {
        String userId = (String) session.getAttribute("loginName");

        if (userId == null || userId.isEmpty()) {
            throw new IllegalStateException("User ID is not found in session.");
        }

        ModelAndView modelAndView = new ModelAndView("create-comment");
        modelAndView.addObject("projectId", projectId);
        modelAndView.addObject("taskId", taskId);
        modelAndView.addObject("pageTitle", "Create Comment");
        modelAndView.addObject("formAction", "/projects/" + projectId + "/tasks/" + taskId + "/comments");
        modelAndView.addObject("buttonText", "Submit");
        modelAndView.addObject("userId", userId); // userId를 ModelAndView에 추가

        return modelAndView;
    }


    @PostMapping(value = "/projects/{projectId}/tasks/{taskId}/comments", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RedirectView postComment(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @RequestBody CommentRequestDTO commentRequestDTO, Model model,
            HttpSession session) {
        try {
            String userId = (String) session.getAttribute("loginName");
            model.addAttribute("userId", userId);
            commentServiceClient.createComment(projectId, taskId, commentRequestDTO, userId);
            return new RedirectView("/projects/" + projectId + "/tasks/" + taskId);
        } catch (Exception e) {
            System.err.println("Error creating comment: " + e.getMessage());
            throw e;
        }
    }


}

