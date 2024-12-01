package com.nhnacademy.minidooray3teamgateway.taskApi.tag;


import com.nhnacademy.minidooray3teamgateway.taskApi.tag.dto.TagRequestDTO;
import com.nhnacademy.minidooray3teamgateway.taskApi.tag.dto.TagResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "tag", url = "http://localhost:8082")
public interface TagServiceClient {

    @PostMapping(value = "/projects/{projectId}/tags", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TagResponseDTO> cratedTag(@PathVariable Long projectId,
                                                    @RequestBody TagRequestDTO tagRequestDTO);

}
