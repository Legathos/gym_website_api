package com.gym_website.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class MonthlyWorkoutsDto {
    private Long userId;
    private int year;
    private int month;
    // Map of day of month to workout (null if no workout for that day)
    private Map<Integer, WorkoutsDto> dailyWorkouts;
}