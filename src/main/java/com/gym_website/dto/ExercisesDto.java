package com.gym_website.dto;

import lombok.Data;

@Data
public class ExercisesDto {
    private Long id;
    private Long user_id;
    private String name;
    private String category;
    private String description;
}
