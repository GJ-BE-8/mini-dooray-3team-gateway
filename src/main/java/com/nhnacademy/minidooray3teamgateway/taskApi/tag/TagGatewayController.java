package com.nhnacademy.minidooray3teamgateway.taskApi.tag;

import com.nhnacademy.minidooray3teamgateway.taskApi.tag.dto.TagRequestDTO;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class TagGatewayController {

    private final TagServiceClient tagServiceClient;

    public TagGatewayController(TagServiceClient tagServiceClient) {
        this.tagServiceClient = tagServiceClient;
    }

    @GetMapping("/projects/{projectId}/tags")
    public ModelAndView getTagPage(@PathVariable Long projectId) {
        return new ModelAndView("create-tag");
    }


    @PostMapping(value = "/projects/{projectId}/tags", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> postTag(@PathVariable Long projectId,
                                                       @RequestBody TagRequestDTO tagRequestDTO) {

        tagServiceClient.cratedTag(projectId, tagRequestDTO);

        return ResponseEntity.ok(Map.of("redirectUrl", "/projects/" + projectId));
    }

}
