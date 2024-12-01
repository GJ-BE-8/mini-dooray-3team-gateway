package com.nhnacademy.minidooray3teamgateway.taskApi.task;


import com.nhnacademy.minidooray3teamgateway.taskApi.task.dto.TaskRequest;
import com.nhnacademy.minidooray3teamgateway.taskApi.task.dto.TaskResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "task", url = "http://localhost:8082")
public interface TaskServiceClient {

    @PostMapping(value = "/projects/{projectId}/tasks", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskResponse> createTask(
            @PathVariable Long projectId,
            @RequestBody TaskRequest taskRequest);

    @GetMapping(value = "/projects/{projectId}/tasks", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TaskResponse>> getAllTasks(
            @PathVariable Long projectId
    );


    @GetMapping("/projects/{projectId}/tasks/{taskId}")
    public ResponseEntity<TaskResponse> getTaskById(
            @PathVariable Long projectId,
            @PathVariable Long taskId);
}
