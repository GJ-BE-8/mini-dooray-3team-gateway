package com.nhnacademy.minidooray3teamgateway.taskApi.comment;


import com.nhnacademy.minidooray3teamgateway.taskApi.comment.dto.CommentRequestDTO;
import com.nhnacademy.minidooray3teamgateway.taskApi.comment.dto.CommentResponseDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "comment", url = "http://localhost:8082")
public interface CommentServiceClient {

    @PostMapping(value = "/projects/{projectId}/tasks/{taskId}/comments", consumes  = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentResponseDTO> createComment(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @Valid @RequestBody CommentRequestDTO commentRequestDTO,
            @RequestHeader("X-User-Id") String userId);

}
