package dto;

import lombok.Data;

import java.util.Date;

@Data
public class LoggerDto {
    private Long id;
    private Long user_id;
    private Date date;
    private Long food_id;
    private Long weight;
    private Long calories;
    private Long carbs;
    private Long fats;
    private Long protein;
}
