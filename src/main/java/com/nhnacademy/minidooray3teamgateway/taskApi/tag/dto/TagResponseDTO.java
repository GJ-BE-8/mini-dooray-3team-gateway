package com.nhnacademy.minidooray3teamgateway.taskApi.tag.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TagResponseDTO {
    private Long tagId;
    private String name;
}