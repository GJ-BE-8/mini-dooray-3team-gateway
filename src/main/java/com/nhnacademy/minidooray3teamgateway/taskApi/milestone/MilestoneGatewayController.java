package com.nhnacademy.minidooray3teamgateway.taskApi.milestone;


import com.nhnacademy.minidooray3teamgateway.taskApi.milestone.dto.MileStoneRequestDTO;
import com.nhnacademy.minidooray3teamgateway.taskApi.milestone.dto.MileStoneResponseDTO;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MilestoneGatewayController {

    private final MileStoneServiceClient mileStoneServiceClient;

    public MilestoneGatewayController(MileStoneServiceClient mileStoneServiceClient) {
        this.mileStoneServiceClient = mileStoneServiceClient;
    }

    @PostMapping(value = "/projects/{projectId}/milestones", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> milestones(@RequestBody MileStoneRequestDTO mileStoneRequestDTO,
                                                          @PathVariable Long projectId) {
        System.out.println("Request to Gateway - projectId: " + projectId);
        System.out.println("Request Body: " + mileStoneRequestDTO);

        try {
            ResponseEntity<MileStoneResponseDTO> response = mileStoneServiceClient.createMileStone(projectId, mileStoneRequestDTO);

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Milestone created successfully in API server: " + response.getBody());
            } else {
                System.err.println("Failed to create milestone in API server: " + response.getStatusCode());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of("error", "Failed to create milestone in API server"));
            }
        } catch (Exception e) {
            System.err.println("Error in Gateway while calling API: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to create milestone."));
        }

        return ResponseEntity.ok(Map.of("redirectUrl", "/projects/" + projectId + "/milestones"));
    }

}
