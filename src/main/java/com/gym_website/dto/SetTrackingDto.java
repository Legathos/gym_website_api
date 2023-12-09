package com.gym_website.dto;

import lombok.Data;

@Data
public class SetTrackingDto {
    private Long id;
    private Long workout_id;
    private Long exercise_id;
    private Long set_id;
    private Long set_number;
    private Long reps;
    private Long weight;
}
