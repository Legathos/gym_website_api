package dto;

import lombok.Data;

@Data
public class FoodDto {
    private Long id;
    private String name;
    private Long weight;
    private Long calories;
    private Long carbs;
    private Long fats;
    private Long protein;
}
