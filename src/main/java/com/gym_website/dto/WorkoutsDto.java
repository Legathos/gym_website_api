package com.gym_website.dto;

import lombok.Data;

import java.util.Date;

@Data
public class WorkoutsDto {
    private Long id;
    private Long user_id;
    private String name;
    private Date date;
    private Long total_weight;
    private Long total_time;
}
