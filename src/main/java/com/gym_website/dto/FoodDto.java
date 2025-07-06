package com.gym_website.dto;

import lombok.Data;

@Data
public class FoodDto {
    private Long id;
    private Long barcode;
    private String name;
    private Double weight;
    private Long calories;
    private Double carbs;
    private Double fats;
    private Double protein;
}
