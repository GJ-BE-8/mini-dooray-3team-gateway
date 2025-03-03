package com.nhnacademy.minidooray3teamgateway.taskApi.milestone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MileStoneRequestDTO {
    private String name;
    private String status;
}
