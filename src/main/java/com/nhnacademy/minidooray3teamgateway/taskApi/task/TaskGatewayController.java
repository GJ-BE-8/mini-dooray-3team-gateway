package com.nhnacademy.minidooray3teamgateway.taskApi.task;


import com.nhnacademy.minidooray3teamgateway.taskApi.milestone.MileStoneServiceClient;
import com.nhnacademy.minidooray3teamgateway.taskApi.milestone.dto.MileStoneResponseDTO;
import com.nhnacademy.minidooray3teamgateway.taskApi.project.dto.ProjectMemberDTO;
import com.nhnacademy.minidooray3teamgateway.taskApi.tag.TagServiceClient;
import com.nhnacademy.minidooray3teamgateway.taskApi.task.dto.TaskRequest;
import com.nhnacademy.minidooray3teamgateway.taskApi.task.dto.TaskResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TaskGatewayController {

    private final TaskServiceClient taskServiceClient;
    private final MileStoneServiceClient mileStoneServiceClient;
    private final TagServiceClient tagServiceClient;

    public TaskGatewayController(TaskServiceClient taskServiceClient, MileStoneServiceClient mileStoneServiceClient,
                                 TagServiceClient tagServiceClient) {
        this.taskServiceClient = taskServiceClient;
        this.mileStoneServiceClient = mileStoneServiceClient;
        this.tagServiceClient = tagServiceClient;
    }

    @GetMapping("/projects/{id}/tasks")
    public ModelAndView tasks(@PathVariable("id") Long projectId) {
        List<TaskResponse> tasks = new ArrayList<>();
        try {
            ResponseEntity<List<TaskResponse>> response = taskServiceClient.getAllTasks(projectId);
            if (response != null && response.getBody() != null) {
                tasks = response.getBody();
            }
        } catch (Exception e) {
            System.err.println("Error fetching tasks: " + e.getMessage());
        }

        tasks.forEach(task -> {
            if (task.getMileStoneDto() == null) {
                task.setMileStoneDto(new MileStoneResponseDTO(null, "No Milestone", "Unknown"));
            }
            if (task.getTags() == null) {
                task.setTags(Collections.emptyList());
            }
            if (task.getComments() == null) {
                task.setComments(Collections.emptyList());
            }
            if (task.getCreatedBy() == null) {
                task.setCreatedBy(new ProjectMemberDTO(null, "Unknown User", "Unknown Role"));
            }
        });

        ModelAndView modelAndView = new ModelAndView("task-list");
        modelAndView.addObject("tasks", tasks);
        modelAndView.addObject("projectId", projectId);

        return modelAndView;
    }

    @GetMapping("/projects/{projectId}/tasks/add")
    public ModelAndView addTask(@PathVariable("projectId") Long projectId) {
        Iterable<MileStoneResponseDTO> show = null;

        try {
            show = mileStoneServiceClient.show(projectId);
        } catch (Exception e) {
            System.err.println("Error fetching milestones: " + e.getMessage());
            e.printStackTrace();
        }

        if (show == null || !show.iterator().hasNext()) {
            System.err.println("Milestones data is empty or null!");
            show = new ArrayList<>();
        }

        ModelAndView modelAndView = new ModelAndView("create-task");
        modelAndView.addObject("milestones", show);
        modelAndView.addObject("projectId", projectId);
        return modelAndView;
    }


    @PostMapping(value = "/projects/{projectId}/tasks", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> addTask(@PathVariable("projectId") Long projectId,
                                                       @RequestBody TaskRequest taskRequest, Model model) {

        try {

            model.addAttribute("projectId", projectId);
            taskServiceClient.createTask(projectId, taskRequest);

            return ResponseEntity.ok(Map.of("redirectUrl", "/projects/" + projectId + "/tasks"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Error creating task: " + e.getMessage()));
        }


    }

    @GetMapping("/projects/{projectId}/tasks/{taskId}")
    public ModelAndView viewTasks(@PathVariable("projectId") Long projectId, @PathVariable Long taskId) {
        ResponseEntity<TaskResponse> taskById = taskServiceClient.getTaskById(projectId, taskId);
        ModelAndView modelAndView = new ModelAndView("task-detail");
        modelAndView.addObject("task", taskById.getBody());
        return modelAndView;
    }
}



