package com.nhnacademy.minidooray3teamgateway.taskApi.milestone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MilestoneSummaryDTO {
    private Long id;
    private String name;
    private String status;
}