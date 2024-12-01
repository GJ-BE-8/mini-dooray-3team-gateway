package com.nhnacademy.minidooray3teamgateway.taskApi.milestone;


import com.nhnacademy.minidooray3teamgateway.taskApi.milestone.dto.MileStoneRequestDTO;
import com.nhnacademy.minidooray3teamgateway.taskApi.milestone.dto.MileStoneResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "milestone", url = "http://localhost:8082")
public interface MileStoneServiceClient {
    @PostMapping(value = "/projects/{projectId}/milestones", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MileStoneResponseDTO> createMileStone(@PathVariable Long projectId,
                                                                @RequestBody MileStoneRequestDTO mileStoneRequestDTO);


    @GetMapping("/projects/{projectId}/milestones")
    public Iterable<MileStoneResponseDTO> show(@PathVariable Long projectId);

}
